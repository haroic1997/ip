package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * Represents a command for adding different subclass of tasks
 */
public class AddCommand  extends Command {

    private Task task;
    public AddCommand(Task task){
        this.task=task;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        tasks.getList().add(tasks.getSize(),task);
        System.out.println("Got it. I've added this task: \n"
                    + tasks.getIndex(tasks.getSize() - 1).getTaskType() + "["
                    + tasks.getIndex(tasks.getSize() - 1).getStatusIcon() + "] "
                    + tasks.getIndex(tasks.getSize() - 1).getFullDescription()
                    + "\nNow you have "+ tasks.getSize()+" tasks in the list."
        );
        Ui.showDivider();
        try{
            storage.appendToFile(task);
        } catch (IOException e){
            System.out.println("Something went wrong: "+ e.getMessage());
        }

    }
}
