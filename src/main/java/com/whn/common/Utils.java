package com.whn.common;

import com.google.gson.Gson;
import com.whn.config.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-10
 * Time: 21:16
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public final class Utils{

    private static final Gson gson = new Gson();

    public static String bytesToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if(hex.length() < 2){
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 读取 byte 转 int
     * 新的方法见 {@link ByteInfo#readInt(int)}
     * @param bytes
     * @param digit
     * @return
     */
    public static int toInt(byte[] bytes, int digit){
        int seq = 0xff;
        int result = 0;
        // 偏移次数, 偏移 bytes.length -1 次
        int excursion = 0;
        for(byte b: bytes){
            result = result | (b & seq);
            if(++excursion < bytes.length){
                result = result << digit;
            }
        }
        return result;
    }

    public static int bytesToInt(byte[] bytes){
        return toInt(bytes, Config.byteSize);
    }

    public static String toJsonString(Object object){
        return gson.toJson(object);
    }

    public static void print(byte[] bytes){
        System.out.println("begin print bytes");
        for (byte b: bytes){
            System.out.println(b);
        }
        System.out.println("end print bytes");
    }

    public static void print(char[] bytes){
        System.out.println("begin print chars");
        for (char ch: bytes){
            System.out.println(ch);
        }
        System.out.println("end print chars");
    }

    public static List newArrayList(int size){
        return new ArrayList(size);
    }

    public static void main(String[] args){

    }
}
