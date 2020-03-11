package pojotime;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author xkl
 * @date 2020/3/11
 * @description
 **/
public class TimeCoder2 extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("客户端接收数据");
        if(in.readableBytes()<4) {
            return;
        }
        UnixTime unixTime = new UnixTime(in.readUnsignedInt());
        out.add(unixTime);
    }
}
