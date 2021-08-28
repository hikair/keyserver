package com.zpq;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zpq.model.KeyUser;
import com.zpq.service.KeyUserService;
import com.zpq.utils.PropertiesUtil;
import com.zpq.utils.SpringUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 客户端每次启动都会连接到服务端,根据来源ip发送按键配置信息给客户端
 * @author 35147
 */
public class KeyConfigServer implements Runnable {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private volatile  boolean stop;
    private KeyUserService keyUserService;

    public KeyConfigServer(){
        try {
            keyUserService = (KeyUserService) SpringUtil.getBean("keyUserService");
            //创建多路复用器 selector
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            //将serverSocketChannel设置为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            //serverSocketChannel的socket绑定服务端口 并设置TCP参数
            //从配置文件中 获取监听端口端口
            Map<String, String> map = PropertiesUtil.getMap("keyServer");
            serverSocketChannel.socket().bind(new InetSocketAddress(Integer.parseInt(map.get("configPort"))), 1024);
            //将serverSocketChannel 注册到selector上,并让selector监听SelectionKey 的OP_ACCEPT操作位
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("发送按键配置线程启动了,等待客户端接入");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        stop = true;
    }

    @Override
    public void run(){
        while (!stop) {
            try {
                //休眠1秒  无论是否有读写事件发生 selector每隔1秒被唤醒
                selector.select(1000);
                //获取注册在selector上的所有的就绪状态的serverSocketChannel中发生的事件
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterators = selectedKeys.iterator();
                SelectionKey key = null;
                while (iterators.hasNext()) {
                    key = iterators.next();
                    iterators.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //服务停止 关闭selector
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 处理客户端新接入的请求
     * @param key 请求信息
     * @throws IOException
     */
    private void handleInput(SelectionKey key) throws IOException{
        if (key.isValid()) {
            //处理新接入的请求消息
            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector,SelectionKey.OP_READ);
            }

            if (key.isReadable()) {
                //获取SocketChannel实例后，相当于完成了TCP三次握手，TCP物理链路正式建立
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                //读取客户端传过来的请求参数
                int readBytes = sc.read(readBuffer);

                if (readBytes > 0) {//获取到了请求数据，对字节进行编解码
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String requestMessage = new String(bytes, Charset.forName("UTF-8"));
                    System.out.println("receive message:"+requestMessage);

                    QueryWrapper<KeyUser> sql = new QueryWrapper<>();
                    sql.eq("host_address",requestMessage);
                    List<KeyUser> keyUsers = keyUserService.list(sql);
                    if(keyUsers.size()==1){
                        doWrite(sc,keyUsers.get(0).getListenKey());
                    }
                } else if (readBytes < 0) {//没有获取到请求数据，需要关闭SocketChannel(C/S 连接链路)
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    /**
     * 将请求的响应 异步返回给客户端
     * @param sc (C/S 连接链路)
     * @param response 响应数据
     * @throws IOException
     */
    private void doWrite(SocketChannel sc, String response) throws IOException {
        if (response != null && !response.isEmpty()) {
            byte[] bytes = response.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            sc.write(buffer);
        }else {
            byte[] bytes = "err".getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            sc.write(buffer);
        }
    }
}
