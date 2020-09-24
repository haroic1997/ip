package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;


/**
 * the base for all other Command classes to inherit from
 */
public abstract class Command {

    private boolean isExit;
    public abstract boolean isExit();
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

}
