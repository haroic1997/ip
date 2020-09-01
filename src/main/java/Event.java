public class Event extends Task {
    protected String at;
    public Event(String description, String at) {
        super(description);
        super.taskType="E";
        this.at = at;
    }
    @Override
    public String getFullDescription() {
        return description + "(at:" + at + ")";
    }
}
