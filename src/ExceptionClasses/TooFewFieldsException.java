
package ExceptionClasses;
/**
 * Exception class for less field than 6 in record
 * @author Aarsh Patel
 */
public class TooFewFieldsException extends Exception{

    private String file;
    private String record;
    private String error = "Too Few Fields";


    public TooFewFieldsException(){
        super("Too few fields detected, cannot add it to any genre file");
    }

    public TooFewFieldsException(String file, String record){
        super();
        this.file = file;
        this.record = record;
    }
    public TooFewFieldsException(String file, String record, String error){
        super();
        this.file = file;
        this.record = record;
        this.error = this.error + error;
    }

    public String getError() {
        return error;
    }

    public void SetError(String error){
        this.error = this.error + error;
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

}// class ExceptionClasses.TooFewFieldsException ends
