package duke;
/**
 * DukeException is used to represent duke-specific exceptions that are likely
 * to occur
 */
public class DukeException extends Exception {
    DukeExceptionType exceptionType;
    public DukeException(DukeExceptionType exceptionType){
        this.exceptionType=exceptionType;
    }
}
