import java.util.Scanner;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String INDENTATION = "     ";
    private static final String LINE = "    ____________________________________________________________";

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DONE = "done";

    private static final String DEADLINE_TIME_PREFIX = " /by ";
    private static final String EVENT_TIME_PREFIX = " /at ";

    private static final int MAX_TASKS = 100;

    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    /**
     * Prints horizontal lines.
     */
    private static void printHorizontalLines() {
        System.out.println(LINE);
    }

    /**
     * Shows welcome message to user.
     */
    private static void showWelcomeMessage() {
        String logo = " ____  _      _  ___\n"
                + "|  _ \\| \\    / |/   \\\n"
                + "| |_| |  \\  /  |  _  | \n"
                + "|____/|   \\/   | | | |\n"
                + "|  _ \\| |\\  /| | |_| |\n"
                + "| |_| | | \\/ | |     |\n"
                + "|____/|_|    |_|\\___/\n";
        System.out.println(logo);
        printHorizontalLines();
        System.out.println(INDENTATION + "Hello! I'm Bmo");
        System.out.println(INDENTATION + "What can I do for you?");
        printHorizontalLines();
        System.out.println();
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     *
     * @return Line entered by the user.
     */
    private static String getUserInput() {
        String userCommand = SCANNER.nextLine();
        while (userCommand.trim().isEmpty()) {
            userCommand = SCANNER.nextLine();
        }
        return userCommand;
    }

    /**
     * Executes the command as specified by the user.
     *
     * @param userCommand Command entered by user.
     *
     * @return Whether to exit the program.
     */
    private static boolean executeCommand(String userCommand) {
        String[] commandTypeAndArgs = splitCommandTypeAndArgs(userCommand);
        String commandType = commandTypeAndArgs[0].trim().toLowerCase();
        String commandArgs = commandTypeAndArgs[1].trim();
        printHorizontalLines();
        switch (commandType) {
        case COMMAND_BYE:
            return executeExitProgram(commandType, commandArgs);
        case COMMAND_LIST:
            executeListTasks(commandType, commandArgs);
            break;
        case COMMAND_TODO:
            executeAddTodoTask(commandType, commandArgs);
            break;
        case COMMAND_DEADLINE:
            executeAddDeadlineTask(commandType, commandArgs);
            break;
        case COMMAND_EVENT:
            executeAddEventTask(commandType, commandArgs);
            break;
        case COMMAND_DONE:
            executeMarkTaskDone(commandType, commandArgs);
            break;
        default:
            showInvalidCommandMessage(commandType);
            System.out.println(INDENTATION + "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
            printHorizontalLines();
            showUsageInfoForAllCommands();
        }
        printHorizontalLines();
        System.out.println();
        return false;
    }

    /**
     * Splits user input into command type and command arguments string.
     *
     * @param userCommand Command entered by user.
     *
     * @return Size 2 array; fist element is the command type and second element is the arguments string.
     */
    private static String[] splitCommandTypeAndArgs(String userCommand) {
        String[] commandTypeAndParams = userCommand.trim().split(" ", 2);
        if (commandTypeAndParams.length != 2) {
            commandTypeAndParams = new String[] {commandTypeAndParams[0], ""};
        }
        return commandTypeAndParams;
    }

    /**
     * Requests to terminate the program.
     *
     * @param commandType Command type entered by the user.
     * @param commandArgs Command arguments entered by the user.
     *
     * @return Whether the request to terminate the program is successful.
     */
    private static boolean executeExitProgram(String commandType, String commandArgs) {
        try {
            if (!commandArgs.isEmpty()) {
                throw new DukeException();
            }
            return true;
        } catch (DukeException e) {
            showInvalidCommandMessage(commandType);
            showUsageInfoForByeCommand();
        }
        return false;
    }

    /**
     * Shows the goodbye message and exits the runtime.
     */
    private static void exitProgram() {
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        printHorizontalLines();
        System.exit(0);
    }

    /**
     * Requests to display all the task in the list to the user.
     *
     * @param commandType Command type entered by the user.
     * @param commandArgs Command arguments entered by the user.
     */
    private static void executeListTasks(String commandType, String commandArgs) {
        try {
            if (!commandArgs.isEmpty()) {
                throw new DukeException();
            }
            listTasks();
        } catch (DukeException e) {
            showInvalidCommandMessage(commandType);
            System.out.println(INDENTATION + "\u2639 OOPS!!! There should not be any arguments after list");
            showUsageInfoForListCommand();
        }
    }

    /**
     * Displays all the tasks in the list to the user.
     */
    private static void listTasks() {
        if (taskCount == 0) {
            System.out.println(INDENTATION + "There are no tasks in your list.");
            return;
        }
        System.out.println(INDENTATION + "Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("     " + (i + 1) + "." + tasks[i]);
        }
    }

    /**
     * Requests to add a todo task to the list.
     *
     * @param commandType Command type entered by the user.
     * @param commandArgs Command arguments entered by the user.
     */
    private static void executeAddTodoTask(String commandType, String commandArgs) {
        try {
            if (commandArgs.isEmpty()) {
                throw new DukeException();
            }
            addTodoTask(commandArgs);
        } catch (DukeException e) {
            showInvalidCommandMessage(commandType);
            System.out.println(INDENTATION + "\u2639 OOPS!!! The description of a todo cannot be empty.");
            showUsageInfoForTodoCommand();
        }
    }

    /**
     * Adds a todo task to the list
     *
     * @param taskDesc Description of the task.
     */
    private static void addTodoTask(String taskDesc) {
        tasks[taskCount] = new Todo(taskDesc);
        taskCount++;
        showTaskAddedMessage(tasks[taskCount - 1]);
    }

    /**
     * Requests to add a deadline task to the list.
     *
     * @param commandType Command type entered by the user.
     * @param commandArgs Command arguments entered by the user.
     */
    private static void executeAddDeadlineTask(String commandType, String commandArgs) {
        try {
            String[] taskDescAndTime = commandArgs.trim().split(DEADLINE_TIME_PREFIX, 2);
            String taskDesc = taskDescAndTime[0].trim();
            String taskTime = taskDescAndTime[1].trim();
            addDeadlineTask(taskDesc, taskTime);
        } catch (IndexOutOfBoundsException e) {
            showInvalidCommandMessage(commandType);
            System.out.println(INDENTATION
                    + "\u2639 OOPS!!! The description or due date/time of a deadline cannot be empty.");
            showUsageInfoForDeadlineCommand();
        }
    }

    /**
     * Adds a deadline task to the list.
     *
     * @param taskDesc Description of the task.
     * @param taskTime Due date/time of the task.
     */
    private static void addDeadlineTask(String taskDesc, String taskTime) {
        tasks[taskCount] = new Deadline(taskDesc, taskTime);
        taskCount++;
        showTaskAddedMessage(tasks[taskCount - 1]);
    }

    /**
     * Requests to add an event task to the list.
     *
     * @param commandType Command type entered by the user.
     * @param commandArgs Command arguments entered by the user.
     */
    private static void executeAddEventTask(String commandType, String commandArgs) {
        try {
            String[] taskDescAndTime = commandArgs.trim().split(EVENT_TIME_PREFIX, 2);
            String taskDesc = taskDescAndTime[0].trim();
            String taskTime = taskDescAndTime[1].trim();
            addEventTask(taskDesc, taskTime);
        } catch (IndexOutOfBoundsException e) {
            showInvalidCommandMessage(commandType);
            System.out.println(INDENTATION
                    + "\u2639 OOPS!!! The description or event date/time of an event cannot be empty.");
            showUsageInfoForEventCommand();
        }
    }

    /**
     * Adds a event task to the list.
     *
     * @param taskDesc Description of the task.
     * @param taskTime Date/time of the task.
     */
    private static void addEventTask(String taskDesc, String taskTime) {
        tasks[taskCount] = new Event(taskDesc, taskTime);
        taskCount++;
        showTaskAddedMessage(tasks[taskCount - 1]);
    }

    /**
     * Requests to mark a task as done.
     *
     * @param commandType Command type entered by the user.
     * @param commandArgs Command arguments entered by the user.
     */
    private static void executeMarkTaskDone(String commandType, String commandArgs) {
        try {
            String[]  split = commandArgs.trim().split(" ");

            if (split.length != 1 || split[0].isEmpty()) {
                throw new DukeException();
            }

            int taskDoneIndex = Integer.parseInt(split[0]) - 1;
            markTaskDone(taskDoneIndex);
        } catch (NumberFormatException e) {
            showInvalidCommandMessage(commandType);
            System.out.println(INDENTATION + "\u2639 OOPS!!! The index of a task needs to be an integer.");
            showUsageInfoForDoneCommand();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            showInvalidCommandMessage(commandType);
            System.out.println(INDENTATION
                    + "\u2639 OOPS!!! The index of a task needs to be within the range of the total number of tasks.");
            showUsageInfoForDoneCommand();
        } catch (DukeException e) {
            showInvalidCommandMessage(commandType);
            System.out.println(INDENTATION
                    + "\u2639 OOPS!!! The index of a task is missing or there are too many arguments.");
            showUsageInfoForDoneCommand();
        }
    }

    /**
     * Marks a task as done.
     *
     * @param taskDoneIndex Index of the task to mark as done
     */
    private static void markTaskDone(int taskDoneIndex) {
        tasks[taskDoneIndex].markAsDone();
        showTaskDoneMessage(tasks[taskDoneIndex]);
    }

    /**
     * Shows a message for the task added by the user.
     *
     * @param task Task added by the user.
     */
    private static void showTaskAddedMessage(Task task) {
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + "  " + task);
        if (taskCount == 1) {
            System.out.println(INDENTATION + "Now you have " + taskCount + " task in the list.");
            return;
        }
        System.out.println(INDENTATION + "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Shows a message for a task that is marked as done.
     *
     * @param task Task to mark as done.
     */
    private static void showTaskDoneMessage(Task task) {
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
    }

    /**
     * Shows a message for an invalid command from user.
     *
     * @param userCommand Command entered by user.
     */
    private static void showInvalidCommandMessage(String userCommand) {
        System.out.println(INDENTATION + "Invalid command format: " + userCommand);
    }

    /**
     * Shows 'bye' command usage instruction.
     */
    private static void showUsageInfoForByeCommand() {
        System.out.println(INDENTATION + "bye: Exits the program.");
        System.out.println(INDENTATION + "Example: bye");
    }

    /**
     * Shows 'list' command usage instruction.
     */
    private static void showUsageInfoForListCommand() {
        System.out.println(INDENTATION + "list: Displays all the tasks in the list.");
        System.out.println(INDENTATION + "Example: list");
    }

    /**
     * Shows 'todo' command usage instruction.
     */
    private static void showUsageInfoForTodoCommand() {
        System.out.println(INDENTATION + "todo: Adds a task without any date/time attached to it.");
        System.out.println(INDENTATION + "Parameters: TASK_DESCRIPTION");
        System.out.println(INDENTATION + "Example: todo borrow book");
    }

    /**
     * Shows 'deadline' command usage instruction.
     */
    private static void showUsageInfoForDeadlineCommand() {
        System.out.println(INDENTATION + "deadline: Adds a task that need to be done before a specific date/time.");
        System.out.println(INDENTATION + "Parameters: TASK_DESCRIPTION /by DATE/TIME");
        System.out.println(INDENTATION + "Example: deadline return book /by Sunday");
    }

    /**
     * Shows 'event' command usage instruction.
     */
    private static void showUsageInfoForEventCommand() {
        System.out.println(INDENTATION
                + "deadline: Adds a task that start at a specific time and ends at a specific time.");
        System.out.println(INDENTATION + "Parameters: TASK_DESCRIPTION /at DATE/TIME");
        System.out.println(INDENTATION + "Example: event project meeting /at Mon 2-4pm");
    }

    /**
     * Shows 'done' command usage instruction.
     */
    private static void showUsageInfoForDoneCommand() {
        System.out.println(INDENTATION + "done: Marks a task as done.");
        System.out.println(INDENTATION + "Parameters: TASK_NUMBER");
        System.out.println(INDENTATION + "Example: done 2");
    }

    /**
     * Shows usage instruction for all commands.
     */
    private static void showUsageInfoForAllCommands() {
        showUsageInfoForListCommand();
        printHorizontalLines();
        showUsageInfoForTodoCommand();
        printHorizontalLines();
        showUsageInfoForDeadlineCommand();
        printHorizontalLines();
        showUsageInfoForEventCommand();
        printHorizontalLines();
        showUsageInfoForDoneCommand();
        printHorizontalLines();
        showUsageInfoForByeCommand();
    }

    public static void main(String[] args) {
        showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            String userCommand = getUserInput();
            isExit = executeCommand(userCommand);
        }
        exitProgram();
    }
}

