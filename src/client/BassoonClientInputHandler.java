package client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
     * Handler implementation for the echo client.  It initiates the ping-pong
     * traffic between the echo client and server by sending the first message to
     * the server.
     */
    public class BassoonClientInputHandler extends ChannelInboundHandlerAdapter {
        StringBuilder builder;

        /**
         * Creates a client-side handler.
         */
        public BassoonClientInputHandler() {
            builder = new StringBuilder();
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            System.out.println("Debug: Channel is active!");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            System.out.print("Debug: Reading... ");
            builder.append(((ByteBuf) msg).toString(CharsetUtil.UTF_16));
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) {
            System.out.println("Received> " + builder.toString());
            builder = new StringBuilder();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            // Close the connection when an exception is raised.
            cause.printStackTrace();
            ctx.close();
        }
    }