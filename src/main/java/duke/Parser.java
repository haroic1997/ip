package duke;

import duke.task.*;

public class Parser {
    public Parser(){}

    public boolean parse(String input,TaskList t,Storage s){
        TaskList tasks=t;
        Storage storage=s;
        String[] words = input.split(" ");
            switch (words[0].toLowerCase()){
            case "bye":
                return true;
            case "list":
                tasks.listContents();
                break;

            case "done":
                int taskNum = Integer.parseInt(words[1]);
                tasks.getIndex(taskNum - 1).maskAsDone();
                System.out.println("Nice! I've marked this task as done:"
                        + "\n"
                        + "["
                        + tasks.getIndex(taskNum - 1).getStatusIcon()
                        + "] "
                        + tasks.getIndex(taskNum - 1).getFullDescription()
                );
                Ui.showDivider();
                break;
            case "delete":
                int taskNumber = Integer.parseInt(words[1]);
                if(tasks.getSize()!=0){
                    tasks.deleteItemFromList(taskNumber-1,storage);
                }
                else{
                    System.out.println("The list is empty! Nothing to delete!");
                }
                break;
            case "todo":
                try{
                    ToDo todo = validateToDo(words);
                    tasks.addToList(todo,storage);
                }catch (DukeException e){
                    switch (e.exceptionType){
                    case MISSING_DESCRIPTION:
                        System.out.println(" The Description of a todo cannot be empty");
                        break;
                    }
                }
                Ui.showDivider();
                break;

            case "deadline":
                try{
                    Deadline deadline = validateDeadline(words);
                    tasks.addToList(deadline,storage);
                }catch (DukeException e){
                    switch (e.exceptionType){
                    case MISSING_DESCRIPTION:
                        System.out.println(" The Description of a Deadline cannot be empty");
                        break;
                    case MISSING_DEADLINE:
                        System.out.println(" The Timing Information of a Deadline cannot be empty");
                        break;
                    }
                }
                Ui.showDivider();
                break;
            case "event":
                try{
                    Event ev = validateEvent(words);
                    tasks.addToList(ev,storage);
                }catch (DukeException e){
                    switch (e.exceptionType){
                    case MISSING_DESCRIPTION:
                        System.out.println(" The Description of a Event cannot be empty");
                        break;
                    case MISSING_EVENT_INFO:
                        System.out.println(" The Logistic Information of a Event cannot be empty");
                        break;
                    }
                }
                Ui.showDivider();
                break;
            default:
                System.out.println("No proper Commands Detected");
                Ui.showDivider();
                break;
            }
            return false;
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
