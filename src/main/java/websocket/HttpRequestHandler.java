package websocket;

import io.netty.channel.*;
import io.netty.handler.codec.Headers;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedFile;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author xkl
 * @date 2020/3/14
 * @description 处理http请求
 **/
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final String wsUri;
   /* private static final File INDEX;

    static{
        URL location = HttpRequestHandler.class.getClassLoader().getResource("index.html");
        try{
            String path = location.toString();
            System.out.println("path:"+path);
            path = !path.contains("file:")?path:path.substring(5);
            INDEX = new File(path);
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Unable to locate index.html", e);
        }
    }*/

    public HttpRequestHandler(String wsUri) {
        this.wsUri = wsUri;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        if(wsUri.equalsIgnoreCase(request.uri())){
            //如果是ws 类型的，交给下一个handler 处理
            ctx.fireChannelRead(request.retain());
        }/*else{
            if(HttpUtil.is100ContinueExpected(request)){
                send100Continue(ctx);
            }

            //响应index.html
            RandomAccessFile file = new RandomAccessFile(INDEX,"r");
            HttpResponse response = new DefaultFullHttpResponse(request.protocolVersion(),HttpResponseStatus.OK);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=UTF-8");
            boolean keepAlive = HttpUtil.isKeepAlive(request);
            if(keepAlive){
                response.headers().set(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
            }
            ctx.write(response);

            if(ctx.pipeline().get(SslHandler.class)==null){
                ctx.write(new DefaultFileRegion(file.getChannel(),0,file.length()));
            }else {
                //如果需要加密，不能直接作用zero-copy的方法
                ctx.write(new ChunkedNioFile(file.getChannel()));
            }

            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            if(!keepAlive){
                //如果不是保持连接，完成之后关闭channel
                future.addListener(ChannelFutureListener.CLOSE);
            }
        }*/
    }

    private static void send100Continue(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
        ctx.writeAndFlush(response);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
