package duke.task;

import java.util.ArrayList;

/**
 * Represents the entire task list. Contains the data of the task list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with the given data.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the total number of tasks in the task list.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Returns the task with {@code taskIndex} from the task list.
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Returns the task list.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Marks the task with {@code taskDoneIndex} in the task list as done.
     */
    public void markTaskDone(int taskDoneIndex) {
        tasks.get(taskDoneIndex).markAsDone();
    }

    /**
     * Deletes the task with {@code taskDeleteIndex} in the task list.
     */
    public void deleteTask(int taskDeleteIndex) {
        tasks.remove(taskDeleteIndex);
    }

}
