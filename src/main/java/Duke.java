import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        boolean byeDetected=false;
        boolean listDetected=false;
        Scanner in = new Scanner(System.in);
        String[] list=new String[100];
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
            listDetected=false;
            input = in.nextLine();
            String[] words = input.split(" ");
            for (String word:words){
                if (word.equalsIgnoreCase("bye")){
                    byeDetected=true;
                    break;
                }
                else if(word.equalsIgnoreCase("list")) {
                    if (listCount==0)
                    {
                        System.out.println("No list Detected, add some text!"+border);
                    }
                    else{
                        for(int i=0;i<listCount;i++){
                            int position =i+1;
                            System.out.println(position +". "+ list[i]);
                        }
                        System.out.println(borderWithoutSkip);
                    }
                    listDetected=true;
                    break;
                }
            }
            if(!byeDetected&&!listDetected)
            {
                list[listCount]=input;
                listCount++;
                System.out.println("added: "+ input+border);
            }

        }
        System.out.println(byeMessage);

    }

}
