package duke.task;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
        super.taskType="T";
    }
    public ToDo(String description, boolean isDone) {
        super(description);
        super.taskType="T";
        super.isDone=isDone;
    }
    @Override
    public String getFullDescription() {
        return description;
    }
}
