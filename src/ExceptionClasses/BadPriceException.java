
package ExceptionClasses;
/**
 * Exception class for invalid price cases
 * @author Aarsh Patel
 */
public class BadPriceException extends Exception{

    private String file;
    private String record;
    private String error = "Invalid Price ";

    public BadPriceException(){
        super("Invalid Price detected, cannot add it to any genre file");
    }

    public BadPriceException(String file, String record){
        super();
        this.file = file;
        this.record = record;
    }
    public BadPriceException(String file, String record, String error){
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



}// class BadPriceException ends
