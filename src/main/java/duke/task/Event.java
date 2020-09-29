package duke.task;

/**
 * Represents an Event task in the task list.
 */
public class Event extends Task {
    public static final String PREFIX = "E";
    protected String at;

    /**
     * Adds a new event task.
     *
     * @param description Description of task.
     * @param at Event time of task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[" + PREFIX + "]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Formats the event task data as a string to be saved in the storage file.
     */
    @Override
    public String toFileFormat() {
        return PREFIX + " | " + super.toFileFormat() + " | " + at;
    }
}

