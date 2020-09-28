package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Task;
import duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

public class DateCommand extends Command {
    public static final String COMMAND_WORD = "date";

    public static final String MESSAGE_USAGE = "     date: Displays deadlines/events occurring on a specific date.\n"
            + "     Parameters: DATE[yyyy-mm-dd]\n"
            + "     Example: date 2020-12-15";

    private final LocalDate date;

    public DateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> dateTasks = tasks.getDateTasks(date);
        int taskCount = dateTasks.size();
        ui.showTaskDate(dateTasks, taskCount);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
