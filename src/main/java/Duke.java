import java.util.Scanner;


public class Duke {
    public static int listCount=0;
    static  Task[] list=new Task[100];
    static final String BORDER="\n________________________________";
    static final String BORDER_WITHOUT_SKIP="________________________________";
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
                +"Whats up people! Duke is here to save the day\n"
                +"Soooo, what can I do for you?\n"
                +"________________________________\n";

        String byeMessage =
                "Aye captain. This is Duke Signing out!\n"
                +"________________________________";

        print(welcomeMessage);


        while (!byeDetected) {
            input = in.nextLine();
            String[] words = input.split(" ");
            String description = "";
            switch (words[0].toLowerCase()) {
            case "bye":
                byeDetected = true;
                break;

            case "list":
                if (listCount == 0) {
                    print("No list Detected, add some text!" + BORDER);
                } else {
                    for (int j = 0; j < listCount; j++) {
                        int position = j + 1;
                        print(position + "." + list[j].getTaskType() + "["
                                + list[j].getStatusIcon() + "] "
                                + list[j].getFullDescription());
                    }
                    printBorder();
                }
                break;

            case "done":

                int taskNum = Integer.parseInt(words[1]);
                list[taskNum - 1].maskAsDone();
                print("Nice! I've marked this task as done:"
                        + "\n"
                        + "["
                        + list[taskNum - 1].getStatusIcon()
                        + "] "
                        + list[taskNum - 1].getFullDescription()
                        + BORDER
                );
                break;

            case "todo":
//                if(words.length==1){
//                    print(" The Description of a todo cannot be empty");
//                }
//                else{
//                    for (int j = 1; j < words.length; j++) {
//                        description += words[j] + " ";
//                    }
//                    ToDo t = new ToDo(description);
//                    addToList(t);
//                }
                try{
                    ToDo t = validateToDo(words);
                    addToList(t);
                }catch (DukeException e){
                    switch (e.exceptionType){
                    case MISSING_INPUT:
                        print(" The Description of a todo cannot be empty");
                        break;
                    }
                }

                printBorder();
                break;

            case "deadline":
                if(words.length==1){
                    print(" The Description of a deadline cannot be empty");
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
                        Deadline d= new Deadline(description,byDescription);
                        addToList(d);
                    }
                    else{
                        print("No Deadline Detected!");
                        printBorder();
                    }
                }

                break;
            case "event":
                if(words.length==1){
                    print(" The Description of a event cannot be empty");
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
                        Event e = new Event(description,atDescription);
                        addToList(e);
                    }
                    else{
                        print("Missing Event location info!");
                        printBorder();
                    }

                }

                break;
            default:{
                print("No proper Commands Detected");
                printBorder();
                break;
            }
            }

        }
        System.out.println(byeMessage);

    }

    public static void addToList(Task t) {

        list[listCount] = t;
        listCount++;
        print("Got it. I've added this task: \n"
                + list[listCount - 1].getTaskType() + "["
                + list[listCount - 1].getStatusIcon() + "] "
                + list[listCount-1].getFullDescription()
                + "\nNow you have "+ listCount+" tasks in the list."
                +BORDER );
    }

    public static void print(String Descriptions){
        System.out.println(Descriptions);
    }
    public static void printBorder(){
        System.out.println(BORDER_WITHOUT_SKIP);
    }

    public static ToDo validateToDo(String[] words) throws DukeException{
        ToDo t;
        String description="";
        if(words.length==1){
            throw new DukeException(DukeExceptionType.MISSING_INPUT);
        }
        else{
            for (int j = 1; j < words.length; j++) {
                description += words[j] + " ";
            }
             t = new ToDo(description);
        }
        return t;
    }
//    public static Deadline validateDeadline(String[] words) throws DukeException{
//        Deadline d;
//        String description="";
//        if(words.length==1){
//            throw new DukeException(DukeExceptionType.MISSING_INPUT);
//        }
//        else{
//            for (int j = 1; j < words.length; j++) {
//                description += words[j] + " ";
//            }
//            d = new Deadline(description,);
//        }
//        return d;
//    }

}
