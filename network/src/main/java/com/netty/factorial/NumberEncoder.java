package com.netty.factorial;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.math.BigInteger;

/**
 * @author dmf
 * @date 2017/12/23
 *
 * 进行编码：42 will be encoded to { 'F', 0, 0, 0, 1, 42 }.
 */
public class NumberEncoder extends MessageToByteEncoder<Number> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Number number, ByteBuf byteBuf) throws Exception {
        BigInteger bigInteger;
        if (number instanceof BigInteger){
            bigInteger = (BigInteger)number;
        }else {
            bigInteger = new BigInteger(String.valueOf(number));
        }

        byte[] bytes = bigInteger.toByteArray();
        int length = bytes.length;

        byteBuf.writeByte((byte)'F');
        byteBuf.writeInt(length);
        byteBuf.writeBytes(bytes);
    }
}
