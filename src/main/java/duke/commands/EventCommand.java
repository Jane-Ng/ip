package duke.commands;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    public static final String COMMAND_PREFIX = " /at ";

    public static final String MESSAGE_USAGE =
            "     event: Adds a task that start at a specific time and ends at a specific time.\n"
            + "     Parameters: TASK_DESCRIPTION /at DATE/TIME\n"
            + "     Example: event project meeting /at Mon 2-4pm";

    private final Task task;

    public EventCommand(String description, String at) {
        this.task = new Event(description, at);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
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
