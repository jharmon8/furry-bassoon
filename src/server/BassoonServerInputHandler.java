package server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.PriorityBlockingQueue;

public class BassoonServerInputHandler extends ChannelInboundHandlerAdapter {
    public PriorityBlockingQueue<GameEvent> q;
    int time = 0;

    public BassoonServerInputHandler(PriorityBlockingQueue<GameEvent> q) {
        this.q = q;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        GameEvent ge = new GameEvent(time++, ((ByteBuf) msg).toString(CharsetUtil.UTF_16), ctx);
        q.add(ge);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        System.out.println("Someone connected!");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        System.out.println("Someone disconnected!");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
