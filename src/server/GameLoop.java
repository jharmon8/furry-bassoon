package server;

import interpreter.WelcomeUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import resources.Player;

import java.util.concurrent.PriorityBlockingQueue;

public class GameLoop implements Runnable {
    PriorityBlockingQueue<GameEvent> inQ;
    public static final String welcome = "WELCOME TO FURRY BASSOON! ENTER USERNAME NOW";

    public GameLoop(PriorityBlockingQueue<GameEvent> inQ) {
        this.inQ = inQ;
    }

    @Override
    public void run() {
        GameEvent ge = null;

        while(true) {
            try {
                if(inQ.isEmpty()) {
                    Thread.sleep(1000); // 1 second for debugging, later we'll do 10 milli or something
                    continue;
                }

                if(inQ.peek().timestamp > System.currentTimeMillis()) {
//                    System.out.println(inQ.peek().timestamp);
//                    System.out.println(System.currentTimeMillis());
                    Thread.sleep(1000); // 1 second for debugging, later we'll do 10 milli or something
                    continue;
                }

                ge = inQ.take();

                switch(ge.type) {
                    case NEWCONN:
                        ge.user.ctx.writeAndFlush(stringToBuffer(welcome));
                        break;
                    case DISCONN:
                        // bye jerk
                        break;
                    case PARSE:
                        switch(ge.user.state) {
                            case USERNAME:
                                if(WelcomeUtil.login(ge.user, ge.userMsg)) { // no passwords for now
                                    ge.user.state = UserState.GAME;
                                    ge.user.ctx.writeAndFlush(stringToBuffer("Welcome " + ge.user.player.username));
                                    // TODO send environment description here
                                } else {
                                    ge.user.welcomeUtil.setUsername(ge.user, ge.userMsg); // enters character creation
//                                    ge.user.state = UserState.CREATION;
//                                    ge.user.ctx.writeAndFlush(stringToBuffer("Player does not exist. Create new player?"));
                                }
                                break;
                            case CREATION:
                                ge.user.welcomeUtil.creationUpdate(ge.user, ge.userMsg);
                                break;
                        }
                        break;
                    case DEBUG:
                        // these just echo for now
                        ge.user.ctx.writeAndFlush(stringToBuffer(ge.userMsg));
                        break;
                    case RESPONSE:
                        ge.user.ctx.writeAndFlush(stringToBuffer("Server says: " + ge.userMsg));
                        break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private ByteBuf stringToBuffer(String str) {
        return Unpooled.copiedBuffer(str, CharsetUtil.UTF_16);
    }
}
