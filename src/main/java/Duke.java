import java.util.Scanner;

public class Duke {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DONE = "done";

    /**
     * Prints horizontal lines.
     */
    private static void printHorizontalLines() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays all the tasks in the list to the user.
     *
     * @param tasks All tasks added by the user.
     * @param tasksCount Count of the total number of tasks.
     */
    private static void listTasks(Task[] tasks, int tasksCount) {
        if (tasksCount == 0) {
            System.out.println("     There are no tasks in your list.");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < tasksCount; i++) {
                System.out.println("     " + (i + 1) + "." + tasks[i]);
            }
        }
    }

    /**
     * Shows a message for the task added by the user.
     *
     * @param task Task added by the user.
     * @param tasksCount Count of the total number of tasks.
     */
    private static void showTaskAddedMessage(Task task, int tasksCount) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        if (tasksCount == 1) {
            System.out.println("     Now you have " + tasksCount + " task in the list.");
        } else {
            System.out.println("     Now you have " + tasksCount + " tasks in the list.");
        }
    }

    /**
     * Shows a message for an invalid command from user.
     *
     * @param command Command entered by user.
     */
    private static void showInvalidCommandMessage(String command) {
        System.out.println("     Invalid command format: " + command);
    }

    /**
     * Shows 'bye' command usage instruction.
     */
    private static void showUsageInfoForByeCommand() {
        System.out.println("     bye: Exits the program.");
        System.out.println("     Example: bye");
    }

    /**
     * Shows 'list' command usage instruction.
     */
    private static void showUsageInfoForListCommand() {
        System.out.println("     list: Displays all the tasks in the list.");
        System.out.println("     Example: list");
    }

    /**
     * Shows 'todo' command usage instruction.
     */
    private static void showUsageInfoForTodoCommand() {
        System.out.println("     todo: Adds a task without any date/time attached to it.");
        System.out.println("     Parameters: TASK_DESCRIPTION");
        System.out.println("     Example: todo borrow book");
    }

    /**
     * Shows 'deadline' command usage instruction.
     */
    private static void showUsageInfoForDeadlineCommand() {
        System.out.println("     deadline: Adds a task that need to be done before a specific date/time.");
        System.out.println("     Parameters: TASK_DESCRIPTION /by DATE/TIME");
        System.out.println("     Example: deadline return book /by Sunday");
    }

    /**
     * Shows 'event' command usage instruction.
     */
    private static void showUsageInfoForEventCommand() {
        System.out.println("     deadline: Adds a task that start at a specific time and ends at a specific time.");
        System.out.println("     Parameters: TASK_DESCRIPTION /at DATE/TIME");
        System.out.println("     Example: event project meeting /at Mon 2-4pm");
    }

    /**
     * Shows 'done' command usage instruction.
     */
    private static void showUsageInfoForDoneCommand() {
        System.out.println("     done: Marks a task as done.");
        System.out.println("     Parameters: TASK_NUMBER");
        System.out.println("     Example: done 2");
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
     * Returns the integer that is parsed from the string
     *
     * @param input Input to be checked if it is an integer
     * @return If valid integer: integer itself
     *         Else: -1,
     */
    private static int checkValidInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        printHorizontalLines();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        printHorizontalLines();
        System.out.println();

        Task[] tasks = new Task[100];
        int tasksCount = 0;
        Scanner in = new Scanner(System.in);

        while (true) {
            String userCommand = in.nextLine();

            while (userCommand.trim().isEmpty()) {
                userCommand = in.nextLine();
            }

            String[] commandTypeAndParams = userCommand.trim().split(" ", 2);
            if (commandTypeAndParams.length != 2) {
                commandTypeAndParams = new String[] {commandTypeAndParams[0], ""};
            }
            String commandType = commandTypeAndParams[0].trim().toLowerCase();
            String commandParams = commandTypeAndParams[1].trim();

            printHorizontalLines();

            switch (commandType) {
            case COMMAND_BYE:
                if (commandParams.isEmpty()) {
                    System.out.println("     Bye. Hope to see you again soon!");
                    printHorizontalLines();
                    System.exit(0);
                } else {
                    showInvalidCommandMessage(commandType);
                    showUsageInfoForByeCommand();
                }
                break;
            case COMMAND_LIST:
                if (commandParams.isEmpty()) {
                    listTasks(tasks, tasksCount);
                } else {
                    showInvalidCommandMessage(commandType);
                    showUsageInfoForListCommand();
                }
                break;
            case COMMAND_TODO:
                if (!commandParams.isEmpty()) {
                    tasks[tasksCount] = new Todo(commandParams);
                    tasksCount++;
                    showTaskAddedMessage(tasks[tasksCount - 1], tasksCount);
                } else {
                    showInvalidCommandMessage(commandType);
                    showUsageInfoForTodoCommand();
                }
                break;
            case COMMAND_DEADLINE:
                String[] taskDescAndTime = commandParams.trim().split(" /by ", 2);
                if (taskDescAndTime.length == 2
                        && !taskDescAndTime[0].isEmpty()
                        && !taskDescAndTime[1].isEmpty()) {
                    String taskDesc = taskDescAndTime[0].trim();
                    String taskTime = taskDescAndTime[1].trim();
                    tasks[tasksCount] = new Deadline(taskDesc, taskTime);
                    tasksCount++;
                    showTaskAddedMessage(tasks[tasksCount - 1], tasksCount);
                } else {
                    showInvalidCommandMessage(commandType);
                    showUsageInfoForDeadlineCommand();
                }
                break;
            case COMMAND_EVENT:
                taskDescAndTime = commandParams.trim().split(" /at ", 2);
                if (taskDescAndTime.length == 2
                        && !taskDescAndTime[0].isEmpty()
                        && !taskDescAndTime[1].isEmpty()) {
                    String taskDesc = taskDescAndTime[0].trim();
                    String taskTime = taskDescAndTime[1].trim();
                    tasks[tasksCount] = new Event(taskDesc, taskTime);
                    tasksCount++;
                    showTaskAddedMessage(tasks[tasksCount - 1], tasksCount);
                } else {
                    showInvalidCommandMessage(commandType);
                    showUsageInfoForEventCommand();
                }
                break;
            case COMMAND_DONE:
                String[]  split = commandParams.trim().split(" ");
                if (split.length == 1 && !split[0].isEmpty()) {
                    int taskDoneIndex = checkValidInteger(split[0]) - 1;
                    if (0 <= taskDoneIndex && taskDoneIndex < tasksCount) {
                        tasks[taskDoneIndex].markAsDone();
                        showTaskDoneMessage(tasks[taskDoneIndex]);
                    } else {
                        showInvalidCommandMessage(commandType);
                        showUsageInfoForDoneCommand();
                    }
                } else {
                    showInvalidCommandMessage(commandType);
                    showUsageInfoForDoneCommand();
                }
                break;
            default:
                showInvalidCommandMessage(commandType);
                printHorizontalLines();
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

            printHorizontalLines();
            System.out.println();
        }
    }
}
