package wtd.command;

import java.util.ArrayList;
import java.util.Arrays;

import wtd.Storage;
import wtd.TaskList;
import wtd.Ui;
import wtd.exceptions.WtdException;
import wtd.exceptions.InvalidArgumentException;

public class Set extends Command {
    protected static ArrayList<String> aliases = new ArrayList<>(Arrays.asList("set", "s"));
    private String command;
    private String newCommand;

    public Set(String command, String newCommand) {
        this.command = command;
        this.newCommand = newCommand;
    }

    @Override
    public String getCommand() {
        return "set";
    }
    
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WtdException {
        switch (command) {
        case "todo":
            CreateTodo.aliases.add(newCommand);
            break;
        case "deadline":
            CreateDeadline.aliases.add(newCommand);
            break;
        case "event":
            CreateEvent.aliases.add(newCommand);
            break;
        case "bye":
            Exit.aliases.add(newCommand);
            break;
        case "list":
            List.aliases.add(newCommand);
            break;
        case "mark":
            Mark.aliases.add(newCommand);
            break;
        case "unmark":
            Unmark.aliases.add(newCommand);
            break;
        case "remove":
            Remove.aliases.add(newCommand);
            break;
        case "find":
            Find.aliases.add(newCommand);
            break;
        case "set":
            Set.aliases.add(newCommand);
            break;
        default:
            throw new InvalidArgumentException(command, "a valid command");
        }
        return ui.showSet(command, newCommand);
    }

    public static boolean checkAlias(String alias) {
       return aliases.contains(alias);
    } 
}
