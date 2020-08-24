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

        String command;
        Scanner in = new Scanner(System.in);
        while (true) {
            command = in.nextLine();
            if (command.equals("bye")) {
                break;
            } else {
                printHorizontalLines();
                System.out.println("     " + command);
                printHorizontalLines();
                System.out.println();
            }
        }

        printHorizontalLines();
        System.out.println("     Bye. Hope to see you again soon!");
        printHorizontalLines();
    }
}
