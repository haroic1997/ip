package duke;

import duke.task.*;
import java.io.FileNotFoundException;



public class Duke {
    public static int listCount=0;
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    static final String BORDER="\n________________________________";
    static final String BORDER_WITHOUT_SKIP="________________________________";
    static final String FILE_PATH="data/duke.txt";

    public Duke(String FILE_PATH){
        ui=new Ui();
        ui.welcomeMessage();
        storage=new Storage(FILE_PATH);
        try {
            tasks=new TaskList(storage.load());
            tasks.listContents();
        }catch ( FileNotFoundException e){
            print("Either Saved File not found or File does not exist yet!");
            tasks = new TaskList();
            storage.createSavedFile();
        }

    }
    public void run(){
        boolean isExit=false;

    while( !isExit ){
        String fullCommand = ui.readCommand();
        try {
            Command c = Parser.parse(fullCommand);
            c.execute(tasks,ui,storage);
            isExit=c.isExit();
        } catch (DukeException e) {
            ui.showError(e.exceptionType);
        } finally {
        }

    }
    ui.byeMessage();
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }

    public static void print(String Descriptions){
        System.out.println(Descriptions);
    }
}
