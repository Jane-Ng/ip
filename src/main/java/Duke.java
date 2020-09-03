import java.util.Scanner;

public class Duke {
    /**
     * Prints horizontal lines.
     */
    public static void printHorizontalLines() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Lists the tasks that are currently stored.
     *
     * @param tasks Tasks Tasks added by the user.
     * @param tasksCount Count of the total number of tasks.
     */
    public static void listTasks(Task tasks[], int tasksCount) {
        if (tasksCount == 0) {
            System.out.println("     There are no tasks in your list.");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < tasksCount; i++) {
                System.out.println("     " + (i + 1) + ".[" + tasks[i].getStatusIcon() + "] "
                        + tasks[i].getDescription());
            }
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
        String command;
        Scanner in = new Scanner(System.in);
        while (true) {
            command = in.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                break;
            }
            printHorizontalLines();
            if (command.equalsIgnoreCase("list")) {
                listTasks(tasks, tasksCount);
            } else if (command.contains("done")) {
                int separatorPosition = command.indexOf(' ');
                int taskDoneIndex = Integer.parseInt(command.substring(separatorPosition + 1)) - 1;
                tasks[taskDoneIndex].markAsDone();
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       [" + tasks[taskDoneIndex].getStatusIcon() + "] "
                        + tasks[taskDoneIndex].getDescription());
            } else {
                tasks[tasksCount] = new Task(command);
                tasksCount++;
                System.out.println("     added: " + command);
            }
            printHorizontalLines();
            System.out.println();
        }

        printHorizontalLines();
        System.out.println("     Bye. Hope to see you again soon!");
        printHorizontalLines();
    }
}
