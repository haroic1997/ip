import java.util.Scanner;


public class Duke {
    public static int listCount=0;
    static  Task[] list=new Task[100];
    static final String BORDER="\n________________________________";
    static final String BORDER_WITHOUT_SKIP="________________________________";
    public static void main(String[] args) {
        /* To check if keywords like bye, list or done is used */
        boolean byeDetected=false;
        boolean commandsDetected=false;
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

        System.out.println(welcomeMessage);


        while (!byeDetected) {
            commandsDetected=false;
            input = in.nextLine();
            String[] words = input.split(" ");
            for (int i=0;i<words.length;i++) {
                switch (words[i].toLowerCase()) {
                case "bye": {
                    byeDetected = true;
                    break;
                }
                case "list": {
                    if (listCount == 0) {
                        System.out.println("No list Detected, add some text!" + BORDER);
                    } else {
                        for (int j = 0; j < listCount; j++) {
                            int position = j + 1;
                            System.out.println(position + "." + list[j].getTaskType() + "["
                                    + list[j].getStatusIcon() + "] "
                                    + list[j].getFullDescription());
                        }
                        System.out.println(BORDER_WITHOUT_SKIP);
                    }
                    commandsDetected = true;
                    break;
                }
                case "done": {

                    int taskNum = Integer.parseInt(words[i + 1]);
                    list[taskNum - 1].maskAsDone();
                    System.out.println("Nice! I've marked this task as done:"
                            + "\n"
                            + "["
                            + list[taskNum - 1].getStatusIcon()
                            + "] "
                            + list[taskNum - 1].getFullDescription()
                            + BORDER
                    );
                    commandsDetected = true;
                    break;
                }
                case "todo": {
                    String description = "";
                    for (int j = 1; j < words.length; j++) {
                        description += words[j] + " ";
                    }
                    ToDo t = new ToDo(description);
                    addToList(t);
                    commandsDetected = true;
                    break;
                }
                case "deadline":{
                    int byPosition=0;
                    String byDescription="";
                    String description = "";
                    for(int j =1; j< words.length;j++){
                        if(words[j].contains("/by")){
                            byPosition=j;
                           break;
                        }
                        else{
                            description+= words[j]+ " ";
                        }
                    }
                    for (int k = byPosition+1; k < words.length; k++) {
                        byDescription= byDescription +" "+ words[k];
                    }
                    Deadline d= new Deadline(description,byDescription);
                    addToList(d);
                    commandsDetected = true;
                    break;
                }
                case "event":{
                    int atPosition=0;
                    String atDescription="";
                    String description = "";
                    for(int j =1; j< words.length;j++){
                        if(words[j].contains("/at")){
                            atPosition=j;
                            break;
                        }
                        else{
                            description+= words[j]+ " ";
                        }
                    }
                    for (int k = atPosition+1; k < words.length; k++) {
                        atDescription= atDescription +" "+ words[k];
                    }
                    Event e= new Event(description,atDescription);
                    addToList(e);
                    commandsDetected = true;
                    break;
                }

                default:{
                    System.out.println("No proper Commands Detected");
                    break;
                }
                }
                if(commandsDetected){
                    break;
                }
            }

        }
        System.out.println(byeMessage);

    }

    public static void addToList(Task t) {

        list[listCount] = t;
        listCount++;
        System.out.println("Got it. I've added this task: \n"
                + list[listCount - 1].getTaskType() + "["
                + list[listCount - 1].getStatusIcon() + "] "
                + list[listCount-1].getFullDescription()
                + "\nNow you have "+ listCount+" tasks in the list."
                +BORDER );
    }

}
