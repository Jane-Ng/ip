package duke.task;

public class Deadline extends Task {
    public static final String PREFIX = "D";
    protected String by;

    /**
     * Adds a new deadline task.
     *
     * @param description Description of task.
     * @param by Deadline time of task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + PREFIX + "]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileFormat() {
        return PREFIX + " | " + super.toFileFormat() + " | " + by;
    }
}

