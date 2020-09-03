public class Event extends Task {
    protected String at;

    /**
     * Adds a new deadline task.
     *
     * @param description Description of task.
     * @param at Event time of task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

