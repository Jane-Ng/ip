package duke.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public void markTaskDone(int taskDoneIndex) {
        tasks.get(taskDoneIndex).markAsDone();
    }

    public void deleteTask(int taskDeleteIndex) {
        tasks.remove(taskDeleteIndex);
    }

    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> keywordTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.description.toLowerCase().contains(keyword)) {
                keywordTasks.add(t);
            }
        }
        return keywordTasks;
    }

}
