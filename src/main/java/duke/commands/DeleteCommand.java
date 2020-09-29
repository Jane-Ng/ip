package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Deletes a task from the task list using the index specified by the user.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = "     delete: Deletes a task.\n"
            + "     Parameters: TASK_NUMBER\n"
            + "     Example: delete 2";

    private final int taskDeleteIndex;

    public DeleteCommand(int taskDeleteIndex) {
        this.taskDeleteIndex = taskDeleteIndex;
    }

    /**
     * @throws DukeException if {@code taskDeleteIndex} is not in range of the total number of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.getTask(taskDeleteIndex);
            tasks.deleteTask(taskDeleteIndex);
            int taskCount = tasks.getTaskCount();
            ui.showTaskDeleted(task, taskCount);
            storage.save(tasks.getAllTasks());
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException("     "
                    + "\u2639 OOPS!!! The task number needs to be within the range of the total number of tasks.\n"
                    + MESSAGE_USAGE);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
