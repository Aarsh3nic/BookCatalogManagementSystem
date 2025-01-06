

package ExceptionClasses;
/**
 * Exception class for unknown genre in record
 * @author Aarsh Patel
 */
public class UnknownGenreException extends Exception{
    private String file;
    private String record;
    private final String  ERROR = "Invalid GENRE";


    public UnknownGenreException(){
        super("Unknown genre detected, cannot add it to any genre file");
    }

    public UnknownGenreException(String file,String record ){
        super();
        this.file = file;
        this.record = record;
    }

    public String getERROR() {
        return ERROR;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

}// class ExceptionClasses.UnknownGenreException ends
