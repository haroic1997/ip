package duke;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{
    private String keywords;
    public FindCommand(String keywords){
        this.keywords=keywords;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> list= new ArrayList<>();
        for (Task t: taskList.getList()){
            if(t.getDescription().contains(keywords)){
                list.add(t);
            }
        }
        int position=1;
        System.out.println("Here are the matching Tasks in your list:");
        for (Task i: list){
            System.out.println(position + "." + "[" + i.getTaskType() + "]" + "["
                    + i.getStatusIcon() + "] "
                    + i.getFullDescription());
            position++;
        }
        Ui.showDivider();

    }
}
