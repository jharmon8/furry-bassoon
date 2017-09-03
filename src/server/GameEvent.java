package server;

import interpreter.WelcomeUtil;
import io.netty.channel.ChannelHandlerContext;

public class GameEvent implements Comparable<GameEvent> {
    public long timestamp;
    public String userMsg;

    public GameEventType type;
    public User user;

    WelcomeUtil welcomeUtil;

    public GameEvent(long timestamp, String userMsg, GameEventType type, User user) {
        this.timestamp = timestamp;
        this.userMsg = userMsg;
        this.type = type;
        this.user = user;
        welcomeUtil = new WelcomeUtil();
    }

    @Override
    public int compareTo(GameEvent o) {
        return (new Long(timestamp)).compareTo(o.timestamp);
    }
}
