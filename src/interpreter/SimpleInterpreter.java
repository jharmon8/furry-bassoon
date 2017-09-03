package interpreter;

public class SimpleInterpreter {

    public static SimpleInterpreterResponse process(String msg) {
        if(msg.equals("y") ||
                msg.equals("yes") ||
                msg.equals("ye")) {
            return SimpleInterpreterResponse.YES;
        }

        if(msg.equals("n") ||
                msg.equals("no") ||
                msg.equals("nah")) {
            return SimpleInterpreterResponse.NO;
        }

        try{
            Integer.parseInt(msg);
            return SimpleInterpreterResponse.INTEG;
        } catch (NumberFormatException e) {}

        if(msg.equals("boy")) {
            return SimpleInterpreterResponse.BOY;
        }

        if(msg.equals("girl")) {
            return SimpleInterpreterResponse.GIRL;
        }

        if(msg.equals("elf")) {
            return SimpleInterpreterResponse.ELF;
        }

        if(msg.equals("human")) {
            return SimpleInterpreterResponse.HUMAN;
        }

        if(msg.equals("tall")) {
            return SimpleInterpreterResponse.TALL;
        }

        if(msg.equals("short")) {
            return SimpleInterpreterResponse.SHORT;
        }

        if(msg.equals("young")) {
            return SimpleInterpreterResponse.YOUNG;
        }

        if(msg.equals("old")) {
            return SimpleInterpreterResponse.OLD;
        }

        return SimpleInterpreterResponse.NONE;
    }
}
