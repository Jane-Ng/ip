package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = "     list: Displays all the tasks in the list.\n"
            + "     Example: list";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> allTasks = tasks.getAllTasks();
        int taskCount = tasks.getTaskCount();
        ui.showTaskList(allTasks, taskCount);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
