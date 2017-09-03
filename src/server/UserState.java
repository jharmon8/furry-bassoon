package server;

public enum UserState {
    USERNAME, // pre username entry, the default user state
    PASSWORD, // the user exists, validating the password
    CREATION, // user didn't exist, creating character
    GAME // playing the game. TODO Player states take it from here
}
