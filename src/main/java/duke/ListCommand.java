package duke;

import duke.task.TaskList;
import duke.task.Ui;

public class ListCommand extends Command{
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
    taskList.listContents();
    }
}
