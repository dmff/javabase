package com.io.netty.demo2.serial;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author dmf
 * @date 2017/12/20
 */
public class Serial2ByteBufferTest {
    
    public static void main(String[] args){
        int id=100;
        int age = 22;
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putInt(id);
        buffer.putInt(age);
        byte[] bytes = buffer.array();
        System.out.println(Arrays.toString(bytes));

        ByteBuffer buffer1 = ByteBuffer.wrap(bytes);
        System.out.println(buffer1.getInt());
        System.out.println(buffer1.getInt());
    }
}
