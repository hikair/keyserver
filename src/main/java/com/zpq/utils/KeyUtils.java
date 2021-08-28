package com.zpq.utils;

/**
 * @author 35147
 */
public class KeyUtils {

    /**
     * 传入特殊键,数字,字母,获得其ascii码
     * @param msg
     * @return
     */
    public static int getKeyCode(String msg){
        if("a".equals(msg)){
            return 65;
        }
        if("b".equals(msg)){
            return 66;
        }
        if("c".equals(msg)){
            return 67;
        }
        if("d".equals(msg)){
            return 68;
        }
        if("e".equals(msg)){
            return 69;
        }
        if("f".equals(msg)){
            return 70;
        }
        if("g".equals(msg)){
            return 71;
        }
        if("h".equals(msg)){
            return 72;
        }
        if("i".equals(msg)){
            return 73;
        }
        if("j".equals(msg)){
            return 74;
        }
        if("k".equals(msg)){
            return 75;
        }
        if("l".equals(msg)){
            return 76;
        }
        if("m".equals(msg)){
            return 77;
        }
        if("n".equals(msg)){
            return 78;
        }
        if("o".equals(msg)){
            return 79;
        }
        if("p".equals(msg)){
            return 80;
        }
        if("q".equals(msg)){
            return 81;
        }
        if("r".equals(msg)){
            return 82;
        }
        if("s".equals(msg)){
            return 83;
        }
        if("t".equals(msg)){
            return 84;
        }
        if("u".equals(msg)){
            return 85;
        }
        if("v".equals(msg)){
            return 86;
        }
        if("w".equals(msg)){
            return 87;
        }
        if("x".equals(msg)){
            return 88;
        }
        if("y".equals(msg)){
            return 89;
        }
        if("z".equals(msg)){
            return 90;
        }
        if("0".equals(msg)){
            return 48;
        }
        if("1".equals(msg)){
            return 49;
        }
        if("2".equals(msg)){
            return 50;
        }
        if("3".equals(msg)){
            return 51;
        }
        if("4".equals(msg)){
            return 52;
        }
        if("5".equals(msg)){
            return 53;
        }
        if("6".equals(msg)){
            return 54;
        }
        if("7".equals(msg)){
            return 55;
        }
        if("8".equals(msg)){
            return 56;
        }
        if("9".equals(msg)){
            return 57;
        }
        if("回车".equals(msg)){
            return 13;
        }
        if("退格".equals(msg)){
            return 8;
        }
        if("删除".equals(msg)){
            return 46;
        }
        if("tab".equals(msg)){
            return 9;
        }
/*        if("shift".equals(msg)){
            return 16;
        }
        if("ctrl".equals(msg)){
            return 17;
        }
        if("alt".equals(msg)){
            return 18;
        }*/
        if("caps".equals(msg)){
            return 20;
        }
        if("esc".equals(msg)){
            return 27;
        }
        if("空格".equals(msg)){
            return 32;
        }
        if("上翻页".equals(msg)){
            return 33;
        }
        if("下翻页".equals(msg)){
            return 34;
        }
        if("end".equals(msg)){
            return 35;
        }
        if("home".equals(msg)){
            return 36;
        }
        if("左箭头".equals(msg)){
            return 37;
        }
        if("上箭头".equals(msg)){
            return 38;
        }
        if("右箭头".equals(msg)){
            return 39;
        }
        if("下箭头".equals(msg)){
            return 40;
        }
        if("insert".equals(msg)){
            return 45;
        }
        if("num".equals(msg)){
            return 144;
        }
        if("分号".equals(msg)){
            return 186;
        }
        if("等号".equals(msg)){
            return 187;
        }
        if("小于号".equals(msg)){
            return 188;
        }
        if("破折号".equals(msg)){
            return 189;
        }
        if("大于号".equals(msg)){
            return 190;
        }
        if("问号".equals(msg)){
            return 191;
        }
        if("波浪号".equals(msg)){
            return 192;
        }
        if("左中括".equals(msg)){
            return 219;
        }
        if("间隔符".equals(msg)){
            return 220;
        }
        if("右中括".equals(msg)){
            return 221;
        }
        if("引号".equals(msg)){
            return 222;
        }
        if("0(x)".equals(msg)){
            return 96;
        }
        if("1(x)".equals(msg)){
            return 97;
        }
        if("2(x)".equals(msg)){
            return 98;
        }
        if("3(x)".equals(msg)){
            return 99;
        }
        if("4(x)".equals(msg)){
            return 100;
        }
        if("5(x)".equals(msg)){
            return 101;
        }
        if("6(x)".equals(msg)){
            return 102;
        }
        if("7(x)".equals(msg)){
            return 103;
        }
        if("8(x)".equals(msg)){
            return 104;
        }
        if("9(x)".equals(msg)){
            return 105;
        }
        if("星号".equals(msg)){
            return 106;
        }
        if("加号".equals(msg)){
            return 107;
        }
        if("减号".equals(msg)){
            return 109;
        }
        if("除号".equals(msg)){
            return 111;
        }
        if("f1".equals(msg)){
            return 112;
        }
        if("f2".equals(msg)){
            return 113;
        }
        if("f3".equals(msg)){
            return 114;
        }
        if("f4".equals(msg)){
            return 115;
        }
        if("f5".equals(msg)){
            return 116;
        }
        if("f6".equals(msg)){
            return 117;
        }
        if("f7".equals(msg)){
            return 118;
        }
        if("f8".equals(msg)){
            return 119;
        }
        if("f9".equals(msg)){
            return 120;
        }
        if("f10".equals(msg)){
            return 121;
        }
        if("f11".equals(msg)){
            return 122;
        }
        if("f12".equals(msg)){
            return 123;
        }
        return -1;
    }

    /**
     * 一个int[1440]的数组 找到它对应的存放位置
     * @param time 17:27:01
     * @return
     */
    public static int findIndexOfMinute(String time){
        //小时数*60+分钟数
        String[] split = time.split(":");
        return Integer.parseInt(split[0])*60+Integer.parseInt(split[1]);
    }

    /**
     * 一个int[12]的数组 找到它对应的存放位置
     * @param time 17:27:01
     * @return
     */
    public static int findIndexOfHour(String time){
        //小时数*60+分钟数
        String[] split = time.split(":");
        return Integer.parseInt(split[0]);
    }
}
