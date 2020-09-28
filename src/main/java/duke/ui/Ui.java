package duke.ui;

import duke.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.common.Messages.MESSAGE_LOGO;
import static duke.common.Messages.MESSAGE_WELCOME;
import static duke.common.Messages.MESSAGE_GOODBYE;
import static duke.common.Messages.MESSAGE_LOADING_ERROR;
import static duke.common.Messages.MESSAGE_TASK_ADDED;
import static duke.common.Messages.MESSAGE_TASK_DONE;
import static duke.common.Messages.MESSAGE_TASK_DELETED;

public class Ui {
    private static final String INDENTATION = "     ";
    public static final String DIVIDER = "    ____________________________________________________________";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String readCommand() {
        String userCommand = in.nextLine();
        while (userCommand.trim().isEmpty()) {
            userCommand = in.nextLine();
        }
        return userCommand;
    }

    public void showLine() {
        out.println(DIVIDER);
    }

    public void showEmptyLine() {
        out.println();
    }

    public void showWelcome() {
        out.println(MESSAGE_LOGO);
        showLine();
        out.println(INDENTATION + MESSAGE_WELCOME);
        showLine();
        showEmptyLine();
    }

    public void showGoodbye() {
        out.println(INDENTATION + MESSAGE_GOODBYE);
    }

    public void showError(String error) {
        out.println(error);
    }

    public void showLoadingError() {
        out.println(MESSAGE_LOADING_ERROR);
    }

    public void showTaskAdded(Task task, int taskCount) {
        out.println(INDENTATION + MESSAGE_TASK_ADDED);
        out.println(INDENTATION + "  " + task);
        if (taskCount == 1) {
            out.println(INDENTATION + "Now you have " + taskCount + " task in the list.");
            return;
        }
        out.println(INDENTATION + "Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskList(ArrayList<Task> tasks, int taskCount) {
        if (taskCount == 0) {
            out.println(INDENTATION + "There are no tasks in your list.");
            return;
        }
        out.println(INDENTATION + "Here are the tasks in your list:");
        for (Task t : tasks) {
            out.println("     " + (tasks.indexOf(t) + 1) + "." + t);
        }
    }

    public void showTaskDone(Task task) {
        out.println(INDENTATION + MESSAGE_TASK_DONE);
        out.println(INDENTATION + "  " + task);
    }

    public void showTaskDeleted(Task task, int taskCount) {
        out.println(INDENTATION + MESSAGE_TASK_DELETED);
        out.println(INDENTATION + "  " + task);
        if (taskCount == 0 || taskCount == 1) {
            out.println(INDENTATION + "Now you have " + taskCount + " task in the list.");
            return;
        }
        out.println(INDENTATION + "Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskFound(ArrayList<Task> tasks, int taskCount) {
        if (taskCount == 0) {
            out.println(INDENTATION + "There are no matching tasks in your list.");
            return;
        }
        out.println(INDENTATION + "Here are the matching tasks in your list:");
        for (Task t : tasks) {
            out.println("     " + (tasks.indexOf(t) + 1) + "." + t);
        }
    }

}
