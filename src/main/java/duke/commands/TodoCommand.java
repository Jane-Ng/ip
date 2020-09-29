package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Adds a todo task to the task list.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = "     todo: Adds a task without any date/time attached to it.\n"
            + "     Parameters: TASK_DESCRIPTION\n"
            + "     Example: todo borrow book";

    private final Task task;

    public TodoCommand(String description) {
        this.task = new Todo(description);
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
