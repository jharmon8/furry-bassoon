package interpreter;

import file.AccountLoader;
import resources.Player;
import server.User;
import server.UserState;

public class WelcomeUtil {
    public String username;
    public String passHash;
    public Player player;

    public WelcomeUtil() {
    }

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
            this.username = username;

            return true;
        }
        return false;
    }

    // TODO
    private static boolean isValidUsername(String username) {
        return true;
    }
}
