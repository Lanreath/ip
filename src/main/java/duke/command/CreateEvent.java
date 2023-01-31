package duke.command;

import java.time.LocalDate;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

/**
 * Represents a command that creates a new Event task.
 */
public class CreateEvent extends Command {
    private String description;
    private LocalDate start;
    private LocalDate end;

    /**
     * Creates a new CreateEvent command.
     *
     * @param description Description of the task.
     * @param start Start date of the task.
     * @param end End date of the task.
     */
    public CreateEvent(String description, LocalDate start, LocalDate end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(new Event(description, start, end, false));
        ui.showAdd(tasks.get(tasks.size() - 1), tasks.size());
    }
}
