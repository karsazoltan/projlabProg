package graphics;

import java.util.ArrayList;

public class Command {
    private String message;
    private String command;
    private boolean hasParameters;
    private String parameterinfo;
    private boolean hasValidOptions;
    private ArrayList<Integer> validOptions;

    public Command(String message, String command) {
        this(message, command, false, "", false, null);
    }

    public Command(String message, String command, String parameterinfo) {
        this(message, command, true, parameterinfo, false, null);
    }

    public Command(String message, String command, String parameterinfo, ArrayList<Integer> validOptions) {
        this(message, command, true, parameterinfo, true, validOptions);
    }

    private Command(String message, String command, boolean hasParameters, String parameterinfo,
                    boolean hasValidOptions, ArrayList<Integer> validOptions) {
        this.message = message;
        this.command = command;
        this.hasParameters = hasParameters;
        this.parameterinfo = parameterinfo;
        this.hasValidOptions = hasValidOptions;
        this.validOptions = validOptions;
    }

    public String getMessage() {
        return message;
    }

    public String getCommand() {
        return command;
    }

    public boolean hasParameters() {
        return hasParameters;
    }

    public String getParameterInfo() {
        return parameterinfo;
    }

    public boolean hasValidOptions() {
        return hasValidOptions;
    }

    public ArrayList<Integer> getValidOptions() {
        return validOptions;
    }
}
