package server;

public enum GameEventType {
    PARSE, // This event needs to be parsed by the game loop
    NEWCONN, // This user has just connected
    DISCONN, // This user has just disconnnected
    DEBUG, // Used by devs to play with stuff
    RESPONSE // Just send a message
}
