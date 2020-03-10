package echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author xkl
 * @date 2020/3/10
 * @description
 **/
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       //将消息写回客户端
        final byte[] bytes = "\r\n".getBytes(CharsetUtil.UTF_8);
        ctx.writeAndFlush(Unpooled.wrappedBuffer(bytes));
        ctx.writeAndFlush(msg);
        ctx.writeAndFlush(Unpooled.wrappedBuffer(bytes));
    }
}
