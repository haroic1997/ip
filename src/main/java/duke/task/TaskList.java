package duke.task;

import duke.Duke;
import duke.Storage;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private  ArrayList<Task> tasks;
    public TaskList(){
        tasks=new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks){
        this.tasks=tasks;
    }

    public Task getIndex(int index){
        return tasks.get(index);
    }
    public int getSize(){
        return tasks.size();
    }

    public ArrayList<Task> getList(){
        return tasks;
    }

    public void listContents(){

        if (tasks.size() == 0) {
            System.out.println("No list Detected, add some text!");
            Ui.showDivider();
        }
        else{
        for (int j = 0; j < tasks.size(); j++) {
            int position = j + 1;
            System.out.println(position + "." + "[" + tasks.get(j).getTaskType() + "]" + "["
                    + tasks.get(j).getStatusIcon() + "] "
                    + tasks.get(j).getFullDescription());
        }
            Ui.showDivider();
    }
    }

    public void deleteItemFromList(int index, Storage s) {
        System.out.println("Nice! I've removed this task:"
                + "\n" + tasks.get(index).getTaskType()
                + "["
                + tasks.get(index).getStatusIcon()
                + "] "
                + tasks.get(index).getFullDescription()
                + "\nNow you have " + (tasks.size() - 1)
                +" tasks in the list."
        );
    tasks.remove(index);
    try{
        s.updateFileContents(tasks);
    }catch (IOException e){
        System.out.println("Problem with saving file!");
    }
        Ui.showDivider();
    }

    public void addToList(Task t,Storage s) {

        tasks.add(tasks.size(),t);
        System.out.println("Got it. I've added this task: \n"
                + tasks.get(tasks.size() - 1).getTaskType() + "["
                + tasks.get(tasks.size() - 1).getStatusIcon() + "] "
                + tasks.get(tasks.size() - 1).getFullDescription()
                + "\nNow you have "+ tasks.size()+" tasks in the list."
                );
        try{
            s.appendToFile(t);
        } catch (IOException e){
            System.out.println("Something went wrong: "+ e.getMessage());
        }
    }



}
