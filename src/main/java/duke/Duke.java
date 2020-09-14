package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Duke {
    public static int listCount=0;
    static  Task[] tasks =new Task[100];
    static final String BORDER="\n________________________________";
    static final String BORDER_WITHOUT_SKIP="________________________________";
    static final String FILE_PATH="data/duke.txt";
    public static void main(String[] args) {
        boolean byeDetected=false;

        Scanner in = new Scanner(System.in);
        String input;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String welcomeMessage = logo +
                "________________________________\n"
                +"Whats up people! duke.Duke is here to save the day\n"
                +"Soooo, what can I do for you?\n"
                +"________________________________\n";

        String byeMessage =
                "Aye captain. This is Duke Signing out!\n"
                +"________________________________";

        print(welcomeMessage);
        try {
            loadFileContents(FILE_PATH);
        }catch (FileNotFoundException e){
            print("Either File not found or File does not exist yet!");
        }

        while (!byeDetected) {
            input = in.nextLine();
            String[] words = input.split(" ");
            switch (words[0].toLowerCase()) {
            case "bye":
                byeDetected = true;
                break;

            case "list":
                listContent();
                break;

            case "done":
                int taskNum = Integer.parseInt(words[1]);
                tasks[taskNum - 1].maskAsDone();
                print("Nice! I've marked this task as done:"
                        + "\n"
                        + "["
                        + tasks[taskNum - 1].getStatusIcon()
                        + "] "
                        + tasks[taskNum - 1].getFullDescription()
                        + BORDER
                );
                break;

            case "todo":
                try{
                    ToDo t = validateToDo(words);
                    addToList(t);
                }catch (DukeException e){
                    switch (e.exceptionType){
                    case MISSING_DESCRIPTION:
                        print(" The Description of a todo cannot be empty");
                        break;
                    }
                }
                printBorder();
                break;

            case "deadline":
                try{
                    Deadline d = validateDeadline(words);
                    addToList(d);
                }catch (DukeException e){
                    switch (e.exceptionType){
                    case MISSING_DESCRIPTION:
                        print(" The Description of a duke.task.Deadline cannot be empty");
                        break;
                    case MISSING_DEADLINE:
                        print(" The Timing Information of a duke.task.Deadline cannot be empty");
                        break;
                    }
                }
                printBorder();
                break;
            case "event":
                try{
                    Event ev = validateEvent(words);
                    addToList(ev);
                }catch (DukeException e){
                    switch (e.exceptionType){
                    case MISSING_DESCRIPTION:
                        print(" The Description of a duke.task.Event cannot be empty");
                        break;
                    case MISSING_EVENT_INFO:
                        print(" The Logistic Information of a duke.task.Event cannot be empty");
                        break;
                    }
                }
                printBorder();
                break;
            default:
                print("No proper Commands Detected");
                printBorder();
                break;
            }

        }
        System.out.println(byeMessage);

    }

    public static void listContent() {
        if (listCount == 0) {
            print("No list Detected, add some text!" + BORDER);
        } else {
            for (int j = 0; j < listCount; j++) {
                int position = j + 1;
                print(position + "." +"["+ tasks[j].getTaskType() +"]"+ "["
                        + tasks[j].getStatusIcon() + "] "
                        + tasks[j].getFullDescription());
            }
            printBorder();
        }
    }

    public static void addToList(Task t) {

        tasks[listCount] = t;
        listCount++;
        print("Got it. I've added this task: \n"+ "["
                + tasks[listCount - 1].getTaskType() +"]"+ "["
                + tasks[listCount - 1].getStatusIcon() + "] "
                + tasks[listCount-1].getFullDescription()
                + "\nNow you have "+ listCount+" tasks in the list."
                );
        try{
            appendToFile(FILE_PATH,t);
        } catch (IOException e){
            print("Something went wrong: "+ e.getMessage());
        }
    }

    public static void print(String Descriptions){
        System.out.println(Descriptions);
    }
    public static void printBorder(){
        System.out.println(BORDER_WITHOUT_SKIP);
    }
    public static void loadFileContents(String filePath) throws FileNotFoundException {
    File f = new File(filePath);
    Scanner s = new Scanner(f);
    int dashPosition;
    int atPosition;
    String description;
    String timedateInfo;
    while(s.hasNext()){
//        print(s.nextLine());
        String contents=s.nextLine();
        dashPosition = contents.indexOf("-");
        String[] words = contents.split("|");
        switch (words[0]){
        case "D":
            atPosition = contents.indexOf("@");
            description = contents.substring(dashPosition+1,atPosition);
            timedateInfo = contents.substring(atPosition+1);
            tasks[listCount]=new Deadline(description.trim(),timedateInfo.trim());
            listCount++;
            break;
        case "E":
            atPosition = contents.indexOf("@");
            description = contents.substring(dashPosition+1,atPosition);
            timedateInfo = contents.substring(atPosition+1);
            tasks[listCount]=new Event(description.trim(),timedateInfo.trim());
            listCount++;
            break;
        case "T":
            description = contents.substring(dashPosition+1);
            tasks[listCount]=new ToDo(description.trim());
            listCount++; break;
        default: break;
        }
    }
    listContent();
    }

    public static void appendToFile(String filePath,Task t) throws IOException{
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
    public static void updateDeletionOfFile(String filePath) throws IOException{
        FileWriter fw = new FileWriter(filePath,true);
        for(int i=0;i<listCount;i++){
            if(tasks[i] instanceof Event){
                fw.write(tasks[i].getTaskType()+"|"+ tasks[i].getStatusIcon()
                        +" - "+tasks[i].getDescription()+" @ " +((Event) tasks[i]).getLocation()
                        +System.lineSeparator());
            }
            else if(tasks[i] instanceof Deadline){
                fw.write(tasks[i].getTaskType()+"|"+ tasks[i].getStatusIcon()
                        +" - "+tasks[i].getDescription()+" @ " +((Deadline) tasks[i]).getTimingInfo()
                        + System.lineSeparator());
            }
            else {
                fw.write(tasks[i].getTaskType()+"|"+ tasks[i].getStatusIcon()
                        +" - "+tasks[i].getDescription()+ System.lineSeparator());
            }
        }
        fw.close();
    }



    public static ToDo validateToDo(String[] words) throws DukeException{
        ToDo t;
        String description="";
        if(words.length==1){
            throw new DukeException(DukeExceptionType.MISSING_DESCRIPTION);
        }
        else{
            for (int j = 1; j < words.length; j++) {
                description += words[j] + " ";
            }
             t = new ToDo(description);
        }
        return t;
    }
    public static Deadline validateDeadline(String[] words) throws DukeException{
        Deadline d;
        String description="";
        if(words.length==1){
            throw new DukeException(DukeExceptionType.MISSING_DESCRIPTION);
        }
        else{
            int byPosition=0;
            String byDescription="";
            for(int j =1; j< words.length;j++){
                if(words[j].contains("/by")){
                    byPosition=j;
                    break;
                }
                else{
                    description+= words[j]+ " ";
                }
            }
            if(byPosition!=0){
                for (int k = byPosition+1; k < words.length; k++) {
                    byDescription= byDescription +" "+ words[k];
                }
                d= new Deadline(description,byDescription);
            }
            else{
                throw new DukeException(DukeExceptionType.MISSING_DEADLINE);
            }
        }
        return d;
    }
    public static Event validateEvent(String[] words) throws DukeException{
        Event e;
        String description="";
        if(words.length==1){
            throw new DukeException(DukeExceptionType.MISSING_DESCRIPTION);
        }
        else{
            int atPosition=0;
            String atDescription="";
            for(int j =1; j< words.length;j++){
                if(words[j].contains("/at")){
                    atPosition=j;
                    break;
                }
                else{
                    description+= words[j]+ " ";
                }
            }
            if(atPosition!=0){
                for (int k = atPosition+1; k < words.length; k++) {
                    atDescription= atDescription +" "+ words[k];
                }
                e= new Event(description,atDescription);
            }
            else{
                throw new DukeException(DukeExceptionType.MISSING_EVENT_INFO);
            }
        }
        return e;
    }


}
