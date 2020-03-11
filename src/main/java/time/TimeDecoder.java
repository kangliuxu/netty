package time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author xkl
 * @date 2020/3/11
 * @description
 **/
public class TimeDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //缓冲池数据不够的时候直接不处理
        if(in.readableBytes()<4){
            return ;
        }
        out.add(in.readBytes(4));
    }
}
