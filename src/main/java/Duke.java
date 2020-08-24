import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        boolean byeDetected=false;
        Scanner in = new Scanner(System.in);
        String input;
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
            input = in.nextLine();
            String[] words = input.split(" ");
            for (String word:words){
                if (word.equalsIgnoreCase("bye")){
                    byeDetected=true;
                }
            }
            if(!byeDetected)
            {
                System.out.println(input);
            }
        }
        System.out.println(byeMessage);

    }



}
