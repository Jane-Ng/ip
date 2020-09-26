package duke.commands;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    public static final String COMMAND_PREFIX = " /by ";

    public static final String MESSAGE_USAGE =
            "     deadline: Adds a task that need to be done before a specific date/time.\n"
            + "     Parameters: TASK_DESCRIPTION /by DATE/TIME\n"
            + "     Example: deadline return book /by Sunday";

    private final Task task;

    public DeadlineCommand(String description, String by) {
        this.task = new Deadline(description, by);
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
