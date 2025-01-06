
package ExceptionClasses;
/**
 * Exception class for more field than 6 in record
 * @author Aarsh Patel
 */
public class TooManyFieldsException extends Exception{

    private String file;
    private String record;
    private String error = "Too Many Fields";


    public TooManyFieldsException(){
    super("Too many fields detected, cannot add it to any genre file");
    }

    public TooManyFieldsException(String file, String record){
        super();
        this.file = file;
        this.record = record;
    }

    public TooManyFieldsException(String file, String record, String error){
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

}//Class ExceptionClasses.TooManyFieldsException ends
