package duke;

import duke.task.TaskList;
import duke.task.Ui;

import java.io.IOException;

public class DoneCommand extends Command {
    private int taskNum;
    public DoneCommand(int taskNum){
        this.taskNum=taskNum;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        taskList.getIndex(taskNum).maskAsDone();
        System.out.println("Nice! I've marked this task as done:"
                + "\n"
                + "["
                + taskList.getIndex(taskNum).getStatusIcon()
                + "] "
                + taskList.getIndex(taskNum).getFullDescription()
        );
        Ui.showDivider();
        try{
            storage.updateFileContents(taskList.getList());
        }catch (IOException e){
            System.out.println("Problem with saving file!");
        }
    }
}
