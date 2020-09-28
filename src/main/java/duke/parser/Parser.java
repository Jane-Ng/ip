package duke.parser;

import duke.commands.Command;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.DoneCommand;
import duke.commands.DeleteCommand;
import duke.commands.DateCommand;
import duke.commands.ByeCommand;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        case DateCommand.COMMAND_WORD:
            return prepareDate(commandArgs);
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
            String[] args = commandArgs.split(DeadlineCommand.DATE_TIME_PREFIX, 2);
            String taskDesc = args[0].trim();
            String[] dateTime = args[1].trim().split(" ", 2);
            LocalDate taskDate = LocalDate.parse(dateTime[0].trim());
            LocalTime taskTime = LocalTime.parse(dateTime[1].trim(), DateTimeFormatter.ofPattern("HHmm"));
            return new DeadlineCommand(taskDesc, taskDate, taskTime);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                    "     \u2639 OOPS!!! The description or due date/time of a deadline cannot be empty.\n"
                    + DeadlineCommand.MESSAGE_USAGE);
        } catch (DateTimeParseException e) {
            throw new DukeException(
                    "     \u2639 OOPS!!! The date and time format of a deadline is wrong.\n"
                            + DeadlineCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareEvent(String commandArgs) throws DukeException {
        try {
            String[] args = commandArgs.split(EventCommand.DATE_TIME_PREFIX, 2);
            String taskDesc = args[0].trim();

            String[] dateTime = args[1].trim().split(" ", 2);
            LocalDate taskDate = LocalDate.parse(dateTime[0].trim());

            String[] time = dateTime[1].trim().split("-", 2);
            LocalTime taskStartTime = LocalTime.parse(time[0].trim(), DateTimeFormatter.ofPattern("HHmm"));
            LocalTime taskEndTime = LocalTime.parse(time[1].trim(), DateTimeFormatter.ofPattern("HHmm"));
            return new EventCommand(taskDesc, taskDate, taskStartTime, taskEndTime);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                    "     \u2639 OOPS!!! The description or event date/time of an event cannot be empty.\n"
                            + EventCommand.MESSAGE_USAGE);
        } catch (DateTimeParseException e) {
            throw new DukeException(
                    "     \u2639 OOPS!!! The date and time format of an event is wrong.\n"
                            + EventCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareDone(String commandArgs) throws DukeException {
        try {
            String[] args = commandArgs.split(" ");

            if (args.length != 1 || args[0].isEmpty()) {
                throw new DukeException(
                        "     \u2639 OOPS!!! The task number is missing or there are too many arguments.\n"
                        + DoneCommand.MESSAGE_USAGE);
            }

            int taskDoneIndex = Integer.parseInt(args[0].trim()) - 1;
            return new DoneCommand(taskDoneIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("     \u2639 OOPS!!! The task number needs to be an integer.\n"
                            + DoneCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareDelete(String commandArgs) throws DukeException {
        try {
            String[] args = commandArgs.split(" ");

            if (args.length != 1 || args[0].isEmpty()) {
                throw new DukeException(
                        "     \u2639 OOPS!!! The task number is missing or there are too many arguments.\n"
                                + DeleteCommand.MESSAGE_USAGE);
            }

            int taskDeleteIndex = Integer.parseInt(args[0].trim()) - 1;
            return new DeleteCommand(taskDeleteIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("     \u2639 OOPS!!! The task number needs to be an integer.\n"
                    + DeleteCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareDate(String commandArgs) throws DukeException {
        try {
            if (commandArgs.isEmpty()) {
                throw new DukeException("     \u2639 OOPS!!! The date is missing.\n"
                        + DateCommand.MESSAGE_USAGE);
            }
            LocalDate taskDate = LocalDate.parse(commandArgs);
            return new DateCommand(taskDate);
        } catch (DateTimeParseException e) {
            throw new DukeException(
                    "     \u2639 OOPS!!! The date format is wrong.\n"
                            + DateCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareBye(String commandArgs) throws DukeException {
        if (!commandArgs.isEmpty()) {
            throw new DukeException("     \u2639 OOPS!!! The command bye should not have any arguments.\n"
                    + ByeCommand.MESSAGE_USAGE);
        }
        return new ByeCommand();
    }
}
