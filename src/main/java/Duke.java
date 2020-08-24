import java.util.Scanner;

public class Duke {
    public static void printHorizontalLines() {
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        printHorizontalLines();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        printHorizontalLines();
        System.out.println();

        String[] tasks = new String[100];
        int numOfTasks = 0;
        String command;
        Scanner in = new Scanner(System.in);
        while (true) {
            command = in.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                printHorizontalLines();
                for (int i = 0; i < numOfTasks; i++) {
                    System.out.println("     " + (i + 1) + ". " + tasks[i]);
                }
                printHorizontalLines();
                System.out.println();
            } else {
                printHorizontalLines();
                tasks[numOfTasks] = command;
                numOfTasks++;
                System.out.println("     added: " + command);
                printHorizontalLines();
                System.out.println();
            }
        }

        printHorizontalLines();
        System.out.println("     Bye. Hope to see you again soon!");
        printHorizontalLines();
    }
}
