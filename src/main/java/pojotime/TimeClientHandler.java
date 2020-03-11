package pojotime;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author xkl
 * @date 2020/3/11
 * @description
 **/
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final UnixTime unixTime = (UnixTime) msg;
        System.out.println(unixTime);
        ctx.close();
    }
}
