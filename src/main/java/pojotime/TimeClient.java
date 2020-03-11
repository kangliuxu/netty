package pojotime;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author xkl
 * @date 2020/3/11
 * @description
 **/
public class TimeClient {

    public static void main(String[] args) throws InterruptedException {
        String host = "127.0.0.1";
        int port = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE,true);

            bootstrap.handler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel ch) throws Exception {
                    ch.pipeline().addLast(new TimeCoder2(),new TimeClientHandler());
                }
            });

            // 启动客户端
            ChannelFuture future = bootstrap.connect(host, port).sync();
            System.out.println("客户端启动并连接");

            future.channel().closeFuture().sync();

        }finally {
            workerGroup.shutdownGracefully();
        }
    }
}
