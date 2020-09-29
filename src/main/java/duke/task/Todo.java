package duke.task;

/**
 * Represents a Todo task in the task list.
 */
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

    /**
     * Formats the todo task data as a string to be saved in the storage file.
     */
    @Override
    public String toFileFormat() {
        return PREFIX + " | " + super.toFileFormat();
    }
}
