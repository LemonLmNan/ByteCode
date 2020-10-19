package com.whn.common;

import com.whn.config.Config;

import java.io.DataInputStream;

/**
 * Created by Intellij IDEA
 * Date: 2020-10-12
 * Time: 00:34
 *
 * @Version:
 * @Description:
 * @author: Whn
 */
public final class ByteInfo{
    private static DataInputStream dataInputStream;

    public static void init(DataInputStream dis){
        dataInputStream = dis;
    }

    /**
     * 读取字节
     * @param readByteSize
     * @return
     * @throws Throwable
     */
    public static byte[] readBytes(int readByteSize)throws Throwable{
        if(readByteSize <= 0){
            return new byte[0];
        }
        DataInputStream dis = dataInputStream;
        byte[] bytes = new byte[readByteSize];
        dis.read(bytes);
        return bytes;
    }

    /**
     * 将字节数组转为 int
     * @param readByteSize 从文件中读取的字节数
     * @return
     * @throws Throwable
     */
    public static int readInt(int readByteSize)throws Throwable{

//        // 旧的使用
//        byte[] bytes = readBytes(readByteSize);
//        int result = Utils.bytesToInt(bytes);
//        return result;

        if(1 == readByteSize){
            return dataInputStream.readUnsignedByte();
        }else if(2 == readByteSize){
            return dataInputStream.readUnsignedShort();
        }else if(4 == readByteSize){
            return dataInputStream.readInt();
        }else{
            return 0;
        }
    }

    public static long readLong(int readByteSize)throws Exception{
        if(readByteSize < 0 || readByteSize < 4){
            throw new IllegalArgumentException("readByteSize must over than 4");
        }
        long result;
        if(readByteSize == 4){
            result = dataInputStream.readInt();

        }else if(readByteSize == 8){
            result = dataInputStream.readLong();
        }else{
            throw new IllegalArgumentException("readByteSize must be 4 or 8");
        }
        return result;
    }

    public static float readFloat()throws Throwable{
        return dataInputStream.readFloat();
    }

    public static long readLong()throws Throwable{
        return dataInputStream.readLong();
    }

    public static double readDouble()throws Throwable{
        return dataInputStream.readDouble();
    }

    /**
     * 将字节数组转为 16进制字符串
     * @param readByteSize
     * @return
     * @throws Throwable
     */
    public static String readHexString(int readByteSize)throws Throwable{
        byte[] bytes = readBytes(readByteSize);
        String result = Utils.bytesToHexString(bytes);
        return result;
    }

    /**
     * 显示 utf-8 字符串
     * @param readByteSize
     * @return
     * @throws Throwable
     */
    public static String readString(int readByteSize)throws Throwable{
        byte[] bytes = readBytes(readByteSize);
        return new String(bytes, Config.CHARSET);
    }

    public static String readString(long readByteSize)throws Throwable{
       return readString((int)readByteSize);
    }

    /**
     * 剩余字节数
     * @return
     * @throws Throwable
     */
    public static int available()throws Throwable{
        return dataInputStream.available();
    }

    /**
     * 读取子 Attribute
     * @param attributes_count
     * @return
     */

    public static void main(String[] args){
    }

}
