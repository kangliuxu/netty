package pojotime;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author xkl
 * @date 2020/3/11
 * @description
 **/
public class TimeEncoder extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("服务器返回数据2");
        final UnixTime unixTime = (UnixTime) msg;
        System.out.println(unixTime);
        ByteBuf encoded = ctx.alloc().buffer(4);
        encoded.writeInt((int)unixTime.value());
        ctx.write(encoded,promise);
    }
}
