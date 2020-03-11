package time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @author xkl
 * @date 2020/3/11
 * @description 完善数据的读取版本1
 **/
public class TimeClientHandlerProV1 extends ChannelInboundHandlerAdapter {

    private ByteBuf byteBuf;

    /**
     *@描述 ctx监听器，在ctx加入时触发
     *@参数
     *@返回值
     *@创建人  xkl
     *@创建时间  2020/3/11
     *@修改人和其它信息
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        byteBuf = ctx.alloc().buffer(4);
    }
    /**
     *@描述 ctx监听器，在ctx移除时触发
     *@参数
     *@返回值
     *@创建人  xkl
     *@创建时间  2020/3/11
     *@修改人和其它信息
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        byteBuf.release();
        byteBuf = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byteBuf.writeBytes(buf);
        buf.release();
        //打印返回来的时间
        if(byteBuf.readableBytes() >=4){
            long currentTimeMillis = (byteBuf.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
