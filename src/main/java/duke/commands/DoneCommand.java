package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Marks a task in the task list as done using the index specified by the user.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = "     done: Marks a task as done.\n"
            + "     Parameters: TASK_NUMBER\n"
            + "     Example: done 2";

    private final int taskDoneIndex;

    public DoneCommand(int taskDoneIndex) {
        this.taskDoneIndex = taskDoneIndex;
    }

    /**
     * @throws DukeException if {@code taskDoneIndex} is not in range of the total number of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.markTaskDone(taskDoneIndex);
            ui.showTaskDone(tasks.getTask(taskDoneIndex));
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
