package com.xp.brushms.client.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Note:
 * Created by wjw
 * Date: 2016/9/26.14:44
 */
public class Local_Mac {

//    public static  void main(String [] args) throws Exception {
//        InetAddress ia = InetAddress.getLocalHost();//获取本地IP对象
//        System.out.println("MAC ......... "+getMACAddress(ia));
//    }


    //获取MAC地址的方法
    public static  String getMACAddress()throws Exception{

//        windows
        //获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
//        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
//
//        //下面代码是把mac地址拼装成String
//        StringBuffer sb = new StringBuffer();
//
//        for(int i=0;i<mac.length;i++){
//            if(i!=0){
//                sb.append("-");
//            }
//            //mac[i] & 0xFF 是为了把byte转化为正整数
//            String s = Integer.toHexString(mac[i] & 0xFF);
//            sb.append(s.length()==1?0+s:s);
//        }
//
//        //把字符串所有小写字母改为大写成为正规的mac地址并返回
//        return sb.toString().toUpperCase();


//        linux
        String mac = "";
        try
        {
            Process p  = Runtime.getRuntime().exec("ifconfig");

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = br.readLine()) != null)
            {
                Pattern pat = Pattern.compile("\\b\\w+:\\w+:\\w+:\\w+:\\w+:\\w+\\b");
                Matcher mat= pat.matcher(line);
                if(mat.find())
                {
                    mac=mat.group(0);
                    break;
                }
            }
            br.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
      return mac;

    }

    public static String getMacAddr() {
        String MacAddr = "";
        String str = "";
        try {
            NetworkInterface NIC = NetworkInterface.getByName("em1");
            if(NIC==null){
                NIC = NetworkInterface.getByName("eno1");
            }
            byte[] buf = NIC.getHardwareAddress();
            for (int i = 0; i < buf.length; i++) {
                str = str + byteHEX(buf[i]);
            }
            MacAddr = str.toUpperCase();
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return MacAddr;
    }

    public static String byteHEX(byte ib) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a','b', 'c', 'd', 'e', 'f' };
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0F];
        ob[1] = Digit[ib & 0X0F];
        String s = new String(ob);

        return s;
    }

}