package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Adds a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    public static final String DATE_TIME_PREFIX = " /by ";

    public static final String MESSAGE_USAGE =
            "     deadline: Adds a task that needs to be done before a specific date and time.\n"
            + "     Parameters: TASK_DESCRIPTION /by DATE(yyyy-MM-dd) TIME(HHmm)\n"
            + "     Example: deadline return book /by 2020-10-15 1800";

    private final Task task;

    public DeadlineCommand(String description, LocalDate date, LocalTime time) {
        this.task = new Deadline(description, date, time);
    }

    /**
     * @throws DukeException if there is error saving tasks to storage file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        int taskCount = tasks.getTaskCount();
        ui.showTaskAdded(tasks.getTask(taskCount - 1), taskCount);
        storage.save(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
