import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        boolean byeDetected=false;
        boolean commandsDetected=false; // Commands Such as Done/ List
        Scanner in = new Scanner(System.in);
        Task[] list=new Task[100];
        int listCount=0;
        String input;
        String border="\n________________________________";
        String borderWithoutSkip="________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String welcomeMessage=
                "________________________________\n"
                +"Whats up people! Duke is here to save the day\n"
                +"Soooo, what can I do for you?\n"
                +"________________________________\n";

        String byeMessage=
                "Aye captain. This is Duke Signing out!\n"
                +"________________________________";

        System.out.println(welcomeMessage);


        while(!byeDetected){
            commandsDetected=false;
            input = in.nextLine();
            String[] words = input.split(" ");
            for (int i=0;i<words.length;i++){
                switch (words[i].toLowerCase()) {
                case "bye":{
                    byeDetected=true;
                    break; }
                case "list":{
                        if (listCount==0) {
                            System.out.println("No list Detected, add some text!"+border);
                        }
                        else{
                            for(int j=0;j<listCount;j++){
                                int position =j+1;
                                System.out.println(position +"."+"["+list[j].getStatusIcon()+"] " + list[j].description);
                            }
                            System.out.println(borderWithoutSkip);
                        }
                        commandsDetected=true;
                        break;
                    }
                case "done":{

                        int taskNum=Integer.parseInt(words[i+1]);
                        list[taskNum-1].maskAsDone();
                        System.out.println("Nice! I've marked this task as done:"
                                +"\n"
                                +"["
                                + list[taskNum-1].getStatusIcon()
                                +"] "
                                + list[taskNum-1].description
                                + border
                                );
                        commandsDetected=true;
                        break;
                    }
                }
            }
            if(!byeDetected&&!commandsDetected)
            {
                Task t = new Task(input);
                list[listCount]=t;
                listCount++;
                System.out.println("added: "+ input+border);
            }

        }
        System.out.println(byeMessage);

    }

}
