package duke.task;

public class Todo extends Task {
    public static final String PREFIX = "T";

    /**
     * Adds a new todo task.
     *
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + PREFIX + "]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return PREFIX + " | " + super.toFileFormat();
    }
}
