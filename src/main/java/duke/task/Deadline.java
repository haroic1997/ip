package duke.task;

public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        super.taskType="D";
        this.by = by;
    }

    public String getDeadline() {
        return by;
    }
    @Override
    public String getFullDescription() {
        return description + "(by:" + by + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
