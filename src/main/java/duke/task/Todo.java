package duke.task;

public class Todo extends Task {
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
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }
}
