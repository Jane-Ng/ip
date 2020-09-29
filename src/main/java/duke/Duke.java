package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Entry point of the Duke task application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates the Storage object based on the specified path.
     * Loads the tasks from the specified path (if any)
     *
     * @param dirPath directory path of the storage data file
     * @param filePath file path of the storage data file
     */
    public Duke(String dirPath, String filePath) {
        ui = new Ui();
        storage = new Storage(dirPath, filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /** Runs the program until termination. */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
                ui.showEmptyLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data", "data/tasks.txt").run();
    }
}
