package duke;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Ui;



public abstract class Command {

    private boolean isExit;
    public abstract boolean isExit();
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

}
