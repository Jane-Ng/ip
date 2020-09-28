package duke.parser;

import duke.commands.Command;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.DoneCommand;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.ByeCommand;

import duke.exception.DukeException;

import static duke.ui.Ui.DIVIDER;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String[] commandTypeAndArgs = splitCommandTypeAndArgs(fullCommand);
        String commandType = commandTypeAndArgs[0].trim().toLowerCase();
        String commandArgs = commandTypeAndArgs[1].trim();

        switch (commandType) {
        case ListCommand.COMMAND_WORD:
            return prepareList(commandArgs);
        case TodoCommand.COMMAND_WORD:
            return prepareTodo(commandArgs);
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(commandArgs);
        case EventCommand.COMMAND_WORD:
            return prepareEvent(commandArgs);
        case DoneCommand.COMMAND_WORD:
            return prepareDone(commandArgs);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(commandArgs);
        case FindCommand.COMMAND_WORD:
            return prepareFind(commandArgs);
        case ByeCommand.COMMAND_WORD:
            return prepareBye(commandArgs);
        default:
            throw new DukeException("     \u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + DIVIDER + "\n"
                    + ListCommand.MESSAGE_USAGE + "\n"
                    + DIVIDER + "\n"
                    + TodoCommand.MESSAGE_USAGE + "\n"
                    + DIVIDER + "\n"
                    + DeadlineCommand.MESSAGE_USAGE + "\n"
                    + DIVIDER + "\n"
                    + EventCommand.MESSAGE_USAGE + "\n"
                    + DIVIDER + "\n"
                    + DoneCommand.MESSAGE_USAGE + "\n"
                    + DIVIDER + "\n"
                    + DeleteCommand.MESSAGE_USAGE + "\n"
                    + DIVIDER + "\n"
                    + ByeCommand.MESSAGE_USAGE);
        }
    }

    private static String[] splitCommandTypeAndArgs(String userCommand) {
        String[] commandTypeAndParams = userCommand.trim().split(" ", 2);
        if (commandTypeAndParams.length != 2) {
            commandTypeAndParams = new String[]{commandTypeAndParams[0], ""};
        }
        return commandTypeAndParams;
    }

    private static Command prepareList(String commandArgs) throws DukeException {
        if (!commandArgs.isEmpty()) {
            throw new DukeException("     \u2639 OOPS!!! The command list should not have any arguments.\n"
                    + ListCommand.MESSAGE_USAGE);
        }
        return new ListCommand();
    }

    private static Command prepareTodo(String commandArgs) throws DukeException {
        if (commandArgs.isEmpty()) {
            throw new DukeException("     \u2639 OOPS!!! The description of a todo cannot be empty.\n"
                    + TodoCommand.MESSAGE_USAGE);
        }
        return new TodoCommand(commandArgs);
    }

    private static Command prepareDeadline(String commandArgs) throws DukeException {
        try {
            String[] taskDescAndTime = commandArgs.trim().split(DeadlineCommand.COMMAND_PREFIX, 2);
            String taskDesc = taskDescAndTime[0].trim();
            String taskTime = taskDescAndTime[1].trim();
            return new DeadlineCommand(taskDesc, taskTime);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                    "     \u2639 OOPS!!! The description or due date/time of a deadline cannot be empty.\n"
                    + DeadlineCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareEvent(String commandArgs) throws DukeException {
        try {
            String[] taskDescAndTime = commandArgs.trim().split(EventCommand.COMMAND_PREFIX, 2);
            String taskDesc = taskDescAndTime[0].trim();
            String taskTime = taskDescAndTime[1].trim();
            return new EventCommand(taskDesc, taskTime);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                    "     \u2639 OOPS!!! The description or event date/time of an event cannot be empty.\n"
                            + EventCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareDone(String commandArgs) throws DukeException {
        try {
            String[] split = commandArgs.trim().split(" ");

            if (split.length != 1 || split[0].isEmpty()) {
                throw new DukeException(
                        "     \u2639 OOPS!!! The task number is missing or there are too many arguments.\n"
                        + DoneCommand.MESSAGE_USAGE);
            }

            int taskDoneIndex = Integer.parseInt(split[0]) - 1;
            return new DoneCommand(taskDoneIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("     \u2639 OOPS!!! The task number needs to be an integer.\n"
                            + DoneCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareDelete(String commandArgs) throws DukeException {
        try {
            String[] split = commandArgs.trim().split(" ");

            if (split.length != 1 || split[0].isEmpty()) {
                throw new DukeException(
                        "     \u2639 OOPS!!! The task number is missing or there are too many arguments.\n"
                                + DeleteCommand.MESSAGE_USAGE);
            }

            int taskDeleteIndex = Integer.parseInt(split[0]) - 1;
            return new DeleteCommand(taskDeleteIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("     \u2639 OOPS!!! The task number needs to be an integer.\n"
                    + DeleteCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareFind(String commandArgs) throws DukeException {
        if (commandArgs.isEmpty()) {
            throw new DukeException("     \u2639 OOPS!!! The keyword to find is missing.\n"
                    + FindCommand.MESSAGE_USAGE);
        }
        return new FindCommand(commandArgs.toLowerCase());
    }

    private static Command prepareBye(String commandArgs) throws DukeException {
        if (!commandArgs.isEmpty()) {
            throw new DukeException("     \u2639 OOPS!!! The command bye should not have any arguments.\n"
                    + ByeCommand.MESSAGE_USAGE);
        }
        return new ByeCommand();
    }
}