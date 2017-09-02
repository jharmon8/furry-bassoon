package server;

import io.netty.channel.ChannelHandlerContext;

public class GameEvent implements Comparable<GameEvent> {
    public long timestamp;
    public String msg;
    public ChannelHandlerContext ctx;

    public GameEvent(long timestamp, String msg, ChannelHandlerContext ctx) {
        this.timestamp = timestamp;
        this.msg = msg;
        this.ctx = ctx;
    }

    @Override
    public int compareTo(GameEvent o) {
        return (new Long(timestamp)).compareTo(o.timestamp);
    }
}
