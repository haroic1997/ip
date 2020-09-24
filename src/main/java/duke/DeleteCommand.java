package duke;

import duke.task.TaskList;
import duke.task.Ui;

public class DeleteCommand extends Command{
    private int taskNum;

    public DeleteCommand(int taskNum){
        this.taskNum=taskNum;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if(taskList.getSize()!=0){
                    taskList.deleteItemFromList(taskNum,storage);
                }
                else{
                    System.out.println("The list is empty! Nothing to delete!");
                }
    }
}
