package com.netty.factorial;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;

import java.math.BigInteger;
import java.util.List;

/**
 * @author dmf
 * @date 2017/12/23
 */
public class BigIntDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // Wait until the length prefix is available.
        if (byteBuf.readableBytes()<5){
            return;
        }

        byteBuf.markReaderIndex();

        // Check the magic number.
        int magicNumber = byteBuf.readUnsignedByte();
        if (magicNumber!='F'){
            byteBuf.resetReaderIndex();
            throw new CorruptedFrameException("Invalid magic number: " + magicNumber);
        }
        // Wait until the whole data is available.
        int dataLength = byteBuf.readInt();
        if (byteBuf.readableBytes()<dataLength){
            byteBuf.resetReaderIndex();
            return;
        }

        byte[] decoded = new byte[dataLength];
        byteBuf.readBytes(decoded);
        list.add(new BigInteger(decoded));

    }
}
