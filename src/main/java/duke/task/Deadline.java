package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task in the task list.
 */
public class Deadline extends Task {
    public static final String PREFIX = "D";
    protected LocalTime time;

    /**
     * Adds a new deadline task.
     *
     * @param description Description of task.
     * @param date Deadline date of task.
     * @param time Deadline time of task.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[" + PREFIX + "]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                + time.format((DateTimeFormatter.ofPattern("hh:mma"))) + ")";
    }

    /**
     * Formats the deadline task data as a string to be saved in the storage file.
     */
    @Override
    public String toFileFormat() {
        return PREFIX + " | " + super.toFileFormat() + " | " + date + " "
                + time.format((DateTimeFormatter.ofPattern("HHmm")));
    }
}

