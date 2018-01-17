package com.io.netty.demo2.serial;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author dmf
 * @date 2017/12/20
 *
 * 原生jdk的序列化的方式，每一个int占用4个字节
 * 采用大端序列或者小端序列
 */
public class Serial1JdkTest {

    public static void main(String[] args) throws IOException {
        int age = 40;
        int id = 100;
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        arrayOutputStream.write(int2bytes(age));
        arrayOutputStream.write(int2bytes(id));
        byte[] bytes = arrayOutputStream.toByteArray();
        System.out.println(Arrays.toString(bytes));

        //反序列化
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(bytes);
        byte[] idBytes = new byte[4];
        arrayInputStream.read(idBytes);
        System.out.println("id:" + bytes2int(idBytes));

        byte[] ageBytes = new byte[4];
        arrayInputStream.read(ageBytes);
        System.out.println("age:" + bytes2int(ageBytes));
    }

    public static byte[] int2bytes(int i){
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (i>>3*8);
        bytes[1] = (byte) (i>>2*8);
        bytes[2] = (byte) (i>>1*8);
        bytes[3] = (byte) (i>>0*8);
        return bytes;
    }

    public static int bytes2int(byte[] bytes){
        return (bytes[0]<<3*8)|(bytes[1]<<2*8)|(bytes[2]<<1*8)|(bytes[3]<<0*8);
    }
}
