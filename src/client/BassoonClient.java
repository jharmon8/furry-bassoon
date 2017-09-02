package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

public final class BassoonClient {

        static final boolean SSL = System.getProperty("ssl") != null;
        static final String HOST = System.getProperty("host", "127.0.0.1");
        static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

        public static void main(String[] args) throws Exception {
            // Configure SSL.git
            final SslContext sslCtx;
            if (SSL) {
                sslCtx = SslContextBuilder.forClient()
                        .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
            } else {
                sslCtx = null;
            }

            // Configure the client.
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap b = new Bootstrap();
                b.group(group)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline p = ch.pipeline();
                                if (sslCtx != null) {
                                    p.addLast(sslCtx.newHandler(ch.alloc(), HOST, PORT));
                                }
                                //p.addLast(new LoggingHandler(LogLevel.INFO));
                                p.addLast(new BassoonClientInputHandler());
                            }
                        });

                // Start the client.
                ChannelFuture f = b.connect(HOST, PORT).sync();
                Channel ch = f.channel();
                ChannelFuture cf = null;

                // Type and send messages.
                String line = "";
                Scanner scan = new Scanner(System.in);

                while((line = scan.nextLine()) != null) {
                    ByteBuf buf = Unpooled.copiedBuffer(line, CharsetUtil.UTF_16);
                    cf = ch.writeAndFlush(buf);
                }

                // Wait until the connection is closed.
                f.channel().closeFuture().sync();
            } finally {
                // Shut down the event loop to terminate all threads.
                group.shutdownGracefully();
            }
        }
    }