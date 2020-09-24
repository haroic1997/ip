package duke.task;



import duke.DukeExceptionType;

import java.util.Scanner;

public class Ui {
    private Scanner in;
    public Ui(){ in = new Scanner(System.in);
    }

    public void welcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = logo +
                "_____________________________________________\n"
                +"Whats up people! Duke is here to save the day\n"
                +"Soooo, what can I do for you?\n";
        System.out.print(welcomeMessage);
        showDivider();
    }
    public void byeMessage(){
        String byeMessage =
                "Aye captain. This is Duke Signing out!\n";
        System.out.print(byeMessage);
        Ui.showDivider();
    }
    public static void showDivider(){
        System.out.println("_____________________________________________\n");
    }
    public String readCommand(){
        String command=in.nextLine();
        return command;
    }
    public void showError(DukeExceptionType e){
        switch (e){
        case MISSING_DEADLINE:
        case MISSING_EVENT_INFO:
        case WRONG_INPUT_FORMAT:
        case MISSING_DESCRIPTION:
        case INVALID_COMMAND:
            System.out.println("No proper Commands Detected, retype again!");
            showDivider();
            break;
        default:
        }
    }


}
