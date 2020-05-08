package graphics;

public class Command {
    private String message;
    private String command;
    private int parameters;
    private String parameterinfo;

    public Command(String message, String command) {
        this(message, command, 0, "");
    }

    public Command(String message, String command, int parameters) {
        this(message, command, parameters, "Enter parameters:");
    }

    public Command(String message, String command, int parameters, String parameterinfo) {
        this.message = message;
        this.command = command;
        this.parameters = parameters;
        this.parameterinfo = parameterinfo;
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

    public String getParameterInfo() {
        return parameterinfo;
    }
}
