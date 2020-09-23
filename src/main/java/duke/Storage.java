package duke;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private ArrayList<Task> tasks;
    public Storage(String filePath){
        this.filePath=filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        tasks= new ArrayList<>();
        int dashPosition;
        int atPosition;
        String description;
        String timedateInfo;
        while(s.hasNext()){
            String contents=s.nextLine();
            dashPosition = contents.indexOf("-");
            String[] words = contents.split("|");
            switch (words[0]){
            case "D":
                atPosition = contents.indexOf("@");
                description = contents.substring(dashPosition+1,atPosition);
                timedateInfo = contents.substring(atPosition+1);
                tasks.add(new Deadline(description.trim(),timedateInfo.trim()));
                break;
            case "E":
                atPosition = contents.indexOf("@");
                description = contents.substring(dashPosition+1,atPosition);
                timedateInfo = contents.substring(atPosition+1);
                tasks.add(new Event(description.trim(),timedateInfo.trim()));
                break;
            case "T":
                description = contents.substring(dashPosition+1);
                tasks.add(new ToDo(description.trim()));
                break;
            default: break;
            }
        }
        return tasks;
    }
    public void createSavedFile(){
        System.out.println("So we have create a new file duke.txt under data directory for you");
        File f = new File("data");
        boolean isCreated =f.mkdir();
        File g =new File(filePath);

        if(isCreated){
            try{
                g.createNewFile();
            }catch(IOException e){
                System.out.println("Sorry Couldnt create save file!");
            }
        }else{
            System.out.println("Sorry Couldn’t create save file");
        }
    }

    public void updateDeletionOfFile(ArrayList<Task> t) throws IOException{
        tasks=t;
        FileWriter fw0=new FileWriter(filePath);
        fw0.write("");
        FileWriter fw = new FileWriter(filePath,true);
        for(int i=0;i< tasks.size();i++){
            if(tasks.get(i) instanceof Event){
                fw.write(tasks.get(i).getTaskType()+"|"+ tasks.get(i).getStatusIcon()
                        +" - "+tasks.get(i).getDescription()+" @ " +((Event) tasks.get(i)).getLocation()
                        +System.lineSeparator());
            }
            else if(tasks.get(i) instanceof Deadline){
                fw.write(tasks.get(i).getTaskType()+"|"+ tasks.get(i).getStatusIcon()
                        +" - "+tasks.get(i).getDescription()+" @ " +((Deadline) tasks.get(i)).getTimingInfo()
                        + System.lineSeparator());
            }
            else {
                fw.write(tasks.get(i).getTaskType()+"|"+ tasks.get(i).getStatusIcon()
                        +" - "+tasks.get(i).getDescription()+ System.lineSeparator());
            }
        }
        fw.close();
    }
    public void appendToFile(Task t) throws IOException{
        FileWriter fw = new FileWriter(filePath,true);
        if(t instanceof Event){
            fw.write(t.getTaskType()+"|"+ t.getStatusIcon()
                    +" - "+t.getDescription()+" @ " +((Event) t).getLocation()
                    +System.lineSeparator());
        }
        else if(t instanceof Deadline){
            fw.write(t.getTaskType()+"|"+ t.getStatusIcon()
                    +" - "+t.getDescription()+" @ " +((Deadline) t).getTimingInfo()
                    + System.lineSeparator());
        }
        else {
            fw.write(t.getTaskType()+"|"+ t.getStatusIcon()
                    +" - "+t.getDescription()+ System.lineSeparator());
        }
        fw.close();
    }
}
