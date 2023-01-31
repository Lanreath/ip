package duke.command;

import java.time.LocalDate;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

/**
 * Represents a command that creates a new Deadline task.
 */
public class CreateDeadline extends Command {
    private String desc;
    private LocalDate by;

    /**
     * Creates a new CreateDeadline command.
     *
     * @param desc Description of the task.
     * @param by Deadline of the task.
     */
    public CreateDeadline(String desc, LocalDate by) {
        this.desc = desc;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(new Deadline(desc, by, false));
        ui.showAdd(tasks.get(tasks.size() - 1), tasks.size());
    }
}
