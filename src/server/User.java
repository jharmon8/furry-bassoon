package server;

import io.netty.channel.ChannelHandlerContext;
import resources.Player;

/**
 * Unlike Player, this is not an in game character - rather a single connection.
 * e.g. During character creation, a user (with an ip and stuff) will be exist,
 * but the player will equal null until character creation is finished
 */
public class User {
    ChannelHandlerContext ctx;
    Player player;

    public User(ChannelHandlerContext ctx) {
        this.ctx = ctx;
        this.player = null;
    }

    public User(ChannelHandlerContext ctx, Player player) {
        this.ctx = ctx;
        this.player = player;
    }
}
