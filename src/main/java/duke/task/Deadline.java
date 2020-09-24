package duke.task;
/**
 *Inherited from Task object, Deadline class has additional attributes
 * to store deadline timing
 */
public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        super.taskType="D";
        this.by = by;
    }
    public Deadline(String description, String by,Boolean isDone) {
        super(description);
        super.taskType="D";
        this.by = by;
        super.isDone=isDone;
    }

    public String getDeadline() {
        return by;
    }
    @Override
    public String getFullDescription() {
        return description + " (by:" + by + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String getTimingInfo(){
        return by;
    }
}
