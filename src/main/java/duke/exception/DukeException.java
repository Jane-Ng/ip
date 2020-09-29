package duke.exception;

/**
 * Signals that some given data does not fulfil some constraints.
 */
public class DukeException extends Exception {
    public DukeException() {
    }

    /**
     * @param message should contain relevant information on the failed constraint(s).
     */
    public DukeException(String message) {
        super(message);
    }
}
