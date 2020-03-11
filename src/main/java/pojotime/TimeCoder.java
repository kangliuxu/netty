package pojotime;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author xkl
 * @date 2020/3/11
 * @description
 **/
public class TimeCoder extends ReplayingDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        final UnixTime unixTime = new UnixTime(in.readUnsignedInt());
        out.add(unixTime);
    }
}
