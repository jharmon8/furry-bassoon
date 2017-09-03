package interpreter;

import file.AccountLoader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import resources.Player;
import server.User;
import server.UserState;

import static server.UserState.GAME;

public class WelcomeUtil {
    public String passHash;
    public Player player;
    private CreationState state = CreationState.VERIFY;

    public WelcomeUtil() {
        player = new Player();
    }

    // todo no passwords for now
    public static boolean login(User user, String username) {
        if(isValidUsername(username)) {
            Player p = AccountLoader.loadPlayer(username);
            if(p == null) {
                return false;
            }

            user.player = p;
            return true;
        }
        return false;
    }

    public boolean setUsername(User user, String username) {
        if(isValidUsername(username)) {
            player.username = username;
            user.state = UserState.CREATION;
            send(user, CreationState.VERIFY);
            return true;
        }
        return false;
    }

    public void creationUpdate(User user, String msg) {
        SimpleInterpreterResponse simpleResp = SimpleInterpreter.process(msg);
        switch(state) {
            case VERIFY:
                if(simpleResp == SimpleInterpreterResponse.YES) {
                    state = CreationState.MENU;
                } else {
                    user.state = UserState.USERNAME;
                    user.welcomeUtil = new WelcomeUtil();
                }
                break;
            case MENU:
                if(simpleResp == SimpleInterpreterResponse.INTEG) {
                    int choice = Integer.parseInt(msg);
                    switch(choice) {
                        case 1:
                            state = CreationState.GENDER;
                            break;
                        case 2:
                            state = CreationState.AGE;
                            break;
                        case 3:
                            state = CreationState.HEIGHT;
                            break;
                        case 4:
                            state = CreationState.RACE;
                            break;
                        case 5:
                            if(complete()) {
                                state = CreationState.COMPLETION;
                            }
                            break;
                        default:
                            state = CreationState.MENU;
                            break;
                    }
                }
                break;
            case GENDER:
                if(simpleResp == SimpleInterpreterResponse.BOY) {
                    player.gender = "boy";
                    state = CreationState.MENU;
                }
                if(simpleResp == SimpleInterpreterResponse.GIRL) {
                    player.gender = "girl";
                    state = CreationState.MENU;
                }
                break;
            case AGE:
                if(simpleResp == SimpleInterpreterResponse.YOUNG) {
                    player.age = "young";
                    state = CreationState.MENU;
                }
                if(simpleResp == SimpleInterpreterResponse.OLD) {
                    player.age = "old";
                    state = CreationState.MENU;
                }
                break;
            case HEIGHT:
                if(simpleResp == SimpleInterpreterResponse.SHORT) {
                    player.height = "short";
                    state = CreationState.MENU;
                }
                if(simpleResp == SimpleInterpreterResponse.TALL) {
                    player.height = "tall";
                    state = CreationState.MENU;
                }
                break;
            case RACE:
                if(simpleResp == SimpleInterpreterResponse.HUMAN) {
                    player.race = "human";
                    state = CreationState.MENU;
                }
                if(simpleResp == SimpleInterpreterResponse.ELF) {
                    player.race = "elf";
                    state = CreationState.MENU;
                }
                break;
            case COMPLETION:
                if(simpleResp == SimpleInterpreterResponse.YES) {
                    user.player = player;
                    user.state = GAME;
                    state = CreationState.DONE;
                    user.welcomeUtil = new WelcomeUtil();
                    // TODO next event?
                } else {
                    state = CreationState.MENU;
                }
                break;
        }

        send(user, state);
    }

    private boolean complete() {
        if(player.gender != null &&
                player.username != null &&
                player.age != null &&
                player.height != null &&
                player.race != null) {
            return true;
        }
        return false;
    }

    // i don't like this
    private void send(User u, CreationState s) {
        if(s == CreationState.DONE) {
            return;
        }
        u.ctx.writeAndFlush(stringToBuffer(s.msg));
    }

    private ByteBuf stringToBuffer(String str) {
        return Unpooled.copiedBuffer(str, CharsetUtil.UTF_16);
    }

    // TODO
    private static boolean isValidUsername(String username) {
        return true;
    }
    private static boolean isExistingUsername(String username) {
        return false;
    }

    private enum CreationState {
        VERIFY ("Player does not exist. Create new player?"),
        MENU ("[1] Gender\n[2] Age\n[3] Height\n[4] Race\n[5] Done\nSelect option."),
        GENDER ("Boy or girl?"),
        AGE ("Young or old?"),
        HEIGHT ("Tall or short?"),
        RACE ("Elf or Human?"),
        COMPLETION ("Are you sure you're finished?"),
        DONE ("");

        public String msg;
        CreationState(String msg) {
            this.msg = msg;
        }

        /*
        public String getMessage() {
            switch (state) {
                case VERIFY:
                    return "ar";
                case MENU:
                case GENDER:
                case AGE:
                case HEIGHT:
                case RACE:
                case COMPLETION:
            }
            return "";
        }
        */
    }
}
