
package ExceptionClasses;
/**
 * Exception class for missing field in record
 * @author Aarsh Patel
 */
public class MissingFieldException extends Exception{

    private String file;
    private String record;
    private String error ;



    public MissingFieldException(){
        super("one or more fields are missing, cannot add it to any genre file");
    }

    public MissingFieldException(String file, String record, String missingField){
        super();
        this.file = file;
        this.record = record;
        this.error = "missing " + missingField;
    }

    public void setError(String error) {
        this.error = "missing " + error;
    }

    public String getError() {
        return error;
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
}// class ExceptionClasses.MissingFieldException ends
