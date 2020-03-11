package time;

import com.sun.corba.se.internal.CosNaming.BootstrapServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author xkl
 * @date 2020/3/11
 * @description
 **/
public class TimeServer {
    private int port;

    public TimeServer(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap b = new ServerBootstrap();
            //配置启动项
            b.group(bossGroup,workerGroup)
             .channel(NioServerSocketChannel.class)
             .childHandler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 protected void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline().addLast(new TimeServerHandler());
                 }
             })
             .option(ChannelOption.SO_BACKLOG,128)
             .childOption(ChannelOption.SO_KEEPALIVE,true);

            //绑定端口
            ChannelFuture channelFuture = b.bind(port).sync();

            channelFuture.channel().closeFuture().sync();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port;
        if(args.length>0){
            port = Integer.parseInt(args[0]);
        }else{
            port  = 9090;
        }

        new TimeServer(port).run();
    }
}
