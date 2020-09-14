package duke.task;

public class Task {

    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(){
        this.description="";
        this.isDone=false;
    }
    public String getFullDescription() {
        return description;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public String getTaskType() {
        return taskType ;
    }
    public void maskAsDone(){
        this.isDone=true;
    }
}
