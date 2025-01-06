
package ExceptionClasses;
/**
 * Exception class for Invalid ISBN of 10 digits cases
 * @author Aarsh Patel
 */
public class BadIsbn10Exception extends Exception{

    private String file;
    private String record;
    private String error = "Invalid ISBN-10 ";

    public BadIsbn10Exception(){
        super("Invalid ISBN-10 detected, cannot add it to any genre file");
    }

    public BadIsbn10Exception(String file, String record){
        super();
        this.file = file;
        this.record = record;
    }
    public BadIsbn10Exception(String file, String record, String error){
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

}// class BadIsbn10Exception ends
