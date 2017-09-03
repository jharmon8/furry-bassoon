package server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.concurrent.PriorityBlockingQueue;

public class BassoonServerInputHandler extends ChannelInboundHandlerAdapter {
    public PriorityBlockingQueue<GameEvent> q;
    HashMap<ChannelHandlerContext, User> users;
    int time = 0;
    StringBuilder builder;

    public BassoonServerInputHandler(PriorityBlockingQueue<GameEvent> q, HashMap<ChannelHandlerContext, User> users) {
        this.q = q;
        this.users = users;

        builder = new StringBuilder();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        builder.append(((ByteBuf) msg).toString(CharsetUtil.UTF_16));
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        System.out.println("Debug: Someone connected!");

        User u = new User(ctx);
        users.put(ctx, u);

        GameEvent ge = new GameEvent(
                System.currentTimeMillis(),
                null,
                GameEventType.NEWCONN,
                u
        );

        q.add(ge);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        System.out.println("Debug: Someone disconnected!");
        if(users.containsKey(ctx)) {
            users.remove(ctx);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        GameEvent ge = new GameEvent(
                System.currentTimeMillis(),
                builder.toString().toLowerCase(),
                GameEventType.PARSE,
                users.get(ctx)
        );

        q.add(ge);
        builder = new StringBuilder();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
