public class DukeException extends Exception{
    DukeExceptionType exceptionType;
    public DukeException(DukeExceptionType exceptionType){
        this.exceptionType=exceptionType;
    }
}
