package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    /** Prefix used for getting data from storage file */
    private static final String FILE_PREFIX = " \\| ";

    public final String dirPath;
    public final String filePath;

    public Storage(String dirPath, String filePath) {
        this.dirPath = dirPath;
        this.filePath = filePath;
    }

    /**
     * Loads the task list data from the storage file.
     *
     * @return the task list data
     * @throws DukeException if the file does not exist
     */
    public ArrayList<Task> load() throws DukeException {
        if (!checkDirExists(dirPath) || !checkFileExists(filePath)) {
            throw new DukeException();
        }

        File dataFile = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        int taskCount = 0;
        try {
            Scanner reader = new Scanner(dataFile);
            while (reader.hasNext()) {
                String data = reader.nextLine();
                String[] dataParts = data.split(FILE_PREFIX);
                String type = dataParts[0];
                String status = dataParts[1];
                String description = dataParts[2];

                switch (type) {
                case Todo.PREFIX:
                    tasks.add(new Todo(description));
                    taskCount++;
                    if (status.equals("1")) {
                        tasks.get(taskCount - 1).markAsDone();
                    }
                    break;
                case Deadline.PREFIX:
                    String by = dataParts[3];
                    tasks.add(new Deadline(description, by));
                    taskCount++;
                    if (status.equals("1")) {
                        tasks.get(taskCount - 1).markAsDone();
                    }
                    break;
                case Event.PREFIX:
                    String at = dataParts[3];
                    tasks.add(new Event(description, at));
                    taskCount++;
                    if (status.equals("1")) {
                        tasks.get(taskCount - 1).markAsDone();
                    }
                    break;
                default:
                    System.out.println("Task type not found.");
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new DukeException();
        }

        return tasks;
    }

    /**
     * Saves the {@code tasks} data to the storage file.
     *
     * @throws DukeException if there are errors writing to file
     */
    public void save(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter file = new FileWriter(filePath);
            for (Task task : tasks) {
                file.write(task.toFileFormat() + "\n");
            }
            file.close();
        } catch (IOException e) {
            throw new DukeException("Error writing to file.");
        }
    }

    /**
     * Checks if the directory exists, creates one if it does not exists.
     *
     * @param dirPath path to the directory
     * @return true if the directory exists or is created
     *         false if the directory does not exist and is not created
     */
    private boolean checkDirExists(String dirPath) {
        File dataDir = new File(dirPath);
        boolean dirExists = dataDir.exists();
        boolean dirCreated = false;
        if (!dirExists) {
            dirCreated = createDir(dataDir);
        }
        if (dirCreated) {
            System.out.println("Directory '" + dirPath + "' created successfully");
        }
        return (dirExists || dirCreated);
    }

    /**
     * Creates the directory.
     */
    private boolean createDir(File dataDir) {
        return dataDir.mkdir();
    }

    /**
     * Checks if the storage file exists, creates one if it does not exists.
     *
     * @param filePath path to the storage file
     * @return true if the storage file exists or is created
     *         false if the storage file does not exist and is not created
     */
    private boolean checkFileExists(String filePath) {
        File dataFile = new File(filePath);
        boolean fileExists = dataFile.exists();
        boolean fileCreated = false;
        if (!fileExists) {
            fileCreated = createFile(dataFile);
        }
        if (fileCreated) {
            System.out.println("File '" + filePath + "' created successfully");
        }
        return (fileExists || fileCreated);
    }

    /**
     * Creates the storage file.
     */
    private boolean createFile(File dataFile) {
        try {
            return dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating the file.");
            return false;
        }
    }

}
