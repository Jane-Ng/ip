package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public static final String PREFIX = "E";
    protected LocalTime startTime;
    protected LocalTime endTime;

    /**
     * Adds a new event task.
     *
     * @param description Description of task.
     * @param date Event date of task.
     * @param startTime Event start time of task.
     * @param endTime Event end time of task.
     */
    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[" + PREFIX + "]" + super.toString() + " (at: "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                + startTime.format((DateTimeFormatter.ofPattern("hh:mma"))) + "-"
                + endTime.format((DateTimeFormatter.ofPattern("hh:mma"))) +")";
    }

    @Override
    public String toFileFormat() {
        return PREFIX + " | " + super.toFileFormat() + " | " + date + " "
                + startTime.format((DateTimeFormatter.ofPattern("HHmm"))) + "-"
                + endTime.format((DateTimeFormatter.ofPattern("HHmm")));
    }
}

