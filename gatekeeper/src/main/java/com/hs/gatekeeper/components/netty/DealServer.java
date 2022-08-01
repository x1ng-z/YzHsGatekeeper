package com.hs.gatekeeper.components.netty;

import com.hs.gatekeeper.configuration.NettyConfiguration;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DealServer implements Runnable {
    private EventLoopGroup bossGroup = null;
    private EventLoopGroup workerGroup = null;
    private int port;

    private ChannelInitializer nettyServerInitializer;

    public DealServer(ChannelInitializer nettyServerInitializer,
                      NettyConfiguration nettyConfiguration) {
        this.nettyServerInitializer = nettyServerInitializer;
        this.port = nettyConfiguration.getPort();
    }

    @Override
    public void run() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(nettyServerInitializer)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            /*Bind and start to accept incoming connections.*/
            ChannelFuture f = b.bind(port).sync(); // (7)
            /*Wait until the server socket is closed.
            In this example, this does not happen, but you can do that to gracefully
            shut down your server.*/
            f.channel().closeFuture().sync();
            //channel = f.channel();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public void destory() {
        log.warn("destroy server resources");
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
//        bossGroup = null;
//        workerGroup = null;
    }

}
