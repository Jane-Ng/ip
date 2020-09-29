package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Adds an event task to the task list.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    public static final String DATE_TIME_PREFIX = " /at ";

    public static final String MESSAGE_USAGE =
            "     event: Adds a task that starts at a specific date and time and ends at a specific date and time.\n"
            + "     Parameters: TASK_DESCRIPTION /at DATE[yyyy-mm-dd] START_TIME[HHmm]-END_TIME[HHmm]\n"
            + "     Example: event project meeting /at 2020-12-15 1800-2000";

    private final Task task;

    public EventCommand(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.task = new Event(description, date, startTime, endTime);
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
