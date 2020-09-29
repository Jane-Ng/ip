package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Finds all tasks in the task list with the keyword and display to the user.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = "     find: Finds a task by searching for a keyword.\n"
            + "     Parameters: KEYWORD\n"
            + "     Example: find book";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> keywordTasks = tasks.findTask(keyword);
        int taskCount = keywordTasks.size();
        ui.showTaskFound(keywordTasks, taskCount);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
