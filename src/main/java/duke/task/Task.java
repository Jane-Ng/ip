package duke.task;

/**
 * Represents a Task in the task list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Adds a new task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return Tick or X symbols.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns the status number of the task.
     *
     * @return 1 or 0.
     */
    public String getStatusNumber() {
        return (isDone ? "1" : "0"); //return 1 or 0
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Formats the task data as a string to be saved in the storage file.
     */
    public String toFileFormat() {
        return getStatusNumber() + " | " + description;
    }
}
