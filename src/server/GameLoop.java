package server;

import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

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
                    System.out.println(inQ.peek().timestamp);
                    System.out.println(System.currentTimeMillis());
                    Thread.sleep(1000); // 1 second for debugging, later we'll do 10 milli or something
                    continue;
                }

                ge = inQ.take();

                switch(ge.type) {
                    case NEWCONN:
                        System.out.println("newconn");
                        ge.user.ctx.writeAndFlush(welcome);
                        break;
                    case DISCONN:
                        // bye jerk
                        break;
                    case PARSE:
                        break;
                    case DEBUG:
                        // these just echo for now
                        ge.user.ctx.writeAndFlush("Server says: " + Unpooled.copiedBuffer(ge.userMsg, CharsetUtil.UTF_16));
                        break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
