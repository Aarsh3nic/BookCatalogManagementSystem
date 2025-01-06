
package ExceptionClasses;
/**
 * Exception class for invalid year cases
 * @author Aarsh Patel
 */
public class BadYearException extends Exception{

    private String file;
    private String record;
    private String error = "Invalid year ";


    public BadYearException(){
        super("Invalid year detected, cannot add it to any genre file");
    }

    public BadYearException(String file, String record){
        super();
        this.file = file;
        this.record = record;
    }
    public BadYearException(String file, String record, String error){
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

}// class BadYearException ends
