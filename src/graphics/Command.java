package graphics;

public class Command {
    private String message;
    private String command;
    private int parameters;

    public Command(String message, String command, int params) {
        this.message = message;
        this.command = command;
        this.parameters = params;
    }

    public String getMessage() {
        return message;
    }

    public String getCommand() {
        return command;
    }

    public int getParameters() {
        return parameters;
    }


}
