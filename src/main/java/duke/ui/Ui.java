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

/**
 * UI of the application.
 */
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

    /**
     * Prompts for the command and reads the text entered by the user.
     *
     * @return full command entered by the user
     */
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

    /**
     * Prints the welcome message upon the start of the application.
     */
    public void showWelcome() {
        out.println(MESSAGE_LOGO);
        showLine();
        out.println(INDENTATION + MESSAGE_WELCOME);
        showLine();
        showEmptyLine();
    }

    /**
     * Prints the goodbye message before exiting the application.
     */
    public void showGoodbye() {
        out.println(INDENTATION + MESSAGE_GOODBYE);
    }

    /**
     * Prints the error message specified by {@code error}.
     */
    public void showError(String error) {
        out.println(error);
    }

    /**
     * Prints the loading error message upon error when loading storage file.
     */
    public void showLoadingError() {
        out.println(MESSAGE_LOADING_ERROR);
    }

    /**
     * Shows the task that is added to the user.
     */
    public void showTaskAdded(Task task, int taskCount) {
        out.println(INDENTATION + MESSAGE_TASK_ADDED);
        out.println(INDENTATION + "  " + task);
        if (taskCount == 1) {
            out.println(INDENTATION + "Now you have " + taskCount + " task in the list.");
            return;
        }
        out.println(INDENTATION + "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Shows a list of tasks to the user.
     */
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

    /**
     * Shows the task that is marked as done to the user.
     */
    public void showTaskDone(Task task) {
        out.println(INDENTATION + MESSAGE_TASK_DONE);
        out.println(INDENTATION + "  " + task);
    }

    /**
     * Shows the task that is deleted to the user.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        out.println(INDENTATION + MESSAGE_TASK_DELETED);
        out.println(INDENTATION + "  " + task);
        if (taskCount == 0 || taskCount == 1) {
            out.println(INDENTATION + "Now you have " + taskCount + " task in the list.");
            return;
        }
        out.println(INDENTATION + "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Shows a list of tasks that occurs on a specific date to the user.
     */
    public void showTaskDate(ArrayList<Task> tasks, int taskCount) {
        if (taskCount == 0) {
            out.println(INDENTATION + "There are no tasks occurring on this date.");
            return;
        }
        out.println(INDENTATION + "Here are the tasks occurring on this date:");
        for (Task t : tasks) {
            out.println("     " + (tasks.indexOf(t) + 1) + "." + t);
        }
    }

    /**
     * Shows a list of tasks that has matching keyword to the user.
     */
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
