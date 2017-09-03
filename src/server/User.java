package server;

import interpreter.WelcomeUtil;
import io.netty.channel.ChannelHandlerContext;
import resources.Player;

/**
 * Unlike Player, this is not an in game character - rather a single connection.
 * e.g. During character creation, a user (with an ip and stuff) will be exist,
 * but the player will equal null until character creation is finished
 */
public class User {
    ChannelHandlerContext ctx;
    public Player player;
    public UserState state;

    WelcomeUtil welcomeUtil = null;

    public User(ChannelHandlerContext ctx) {
        this.ctx = ctx;
        this.player = null;
        this.state = UserState.USERNAME;
        this.welcomeUtil = new WelcomeUtil();
    }

    public User(ChannelHandlerContext ctx, Player player) { // probably gonna be deprecated
        this.ctx = ctx;
        this.player = player;
        this.state = UserState.GAME; // idk what this is for
    }
}
