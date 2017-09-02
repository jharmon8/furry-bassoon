package server;

import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.util.concurrent.PriorityBlockingQueue;

public class GameLoop implements Runnable {
    PriorityBlockingQueue<GameEvent> inQ;

    public GameLoop(PriorityBlockingQueue<GameEvent> inQ) {
        this.inQ = inQ;
    }

    @Override
    public void run() {
        GameEvent ge = null;

        while(true) {
            try {
                ge = inQ.take();
                ge.ctx.writeAndFlush(Unpooled.copiedBuffer(ge.msg, CharsetUtil.UTF_16));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
