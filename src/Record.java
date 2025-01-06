

import ExceptionClasses.*;
import java.io.*;
import java.util.Scanner;

/**
 * Represent record in the file
 * Has multiple methods to fetch and validate data out if record.
 * @author Aarsh Patel
 */
public class Record {

    private String[] orderedBookAttr =  {"Title", "Author(s)", "Price", "ISBN", "Genre", "Year"};
    private String[] genres =  {"CCB", "HCB", "MTV", "MRB", "NEB", "OTR", "SSM", "TPA"};

    /**
     * Checks for any empty file from the list present in given file path.
     * @param filePath
     * @return Non-Empty File names list
     * @throws FileNotFoundException
     */
    public String[] getFileNames(String filePath) throws FileNotFoundException{
            Scanner scan = new Scanner(new FileInputStream(filePath));

            int total_files = scan.nextInt();
            scan.nextLine(); // to skip the blank part and reach the next line
            String[] all_files = new String[total_files];
            int counter = 0;
            String recordLine;
            while(scan.hasNextLine()){
                recordLine = scan.nextLine();
                if(isEmptyFile("book_dir/"+ recordLine)){
                    System.out.println("Skipping File: "+recordLine+ " (Either empty or does not exist)\n");
                }
                else{
                    all_files[counter] = recordLine;
                    counter++;
                }
            }

            String[] nonEmptyFiles = new String[counter];

            for(int i=0 ; i<counter ; i++){
                nonEmptyFiles[i] = all_files[i];
            }

            return nonEmptyFiles;

    }//getFileNames() ends

    /**
     * Checks if the given file is empty
     * @param file_name
     * @return True if the file is empty
     * @throws FileNotFoundException
     */
    public boolean isEmptyFile(String file_name) throws FileNotFoundException {

        boolean empty;
        File file = new File(file_name);
        if(file.exists()){
            Scanner scan = new Scanner(new FileInputStream(file_name));
            empty= scan.hasNext();
            scan.close();
            return !empty;
        }
        else{
            return true;
        }

    }//getNonEmptyFiles() ends

    /**
     * Displays START message for PART 1
     */
    public void displayPart_1_StartMessage(){
        System.out.println("||-----------------------------------||");
        System.out.println("| ------ Genre Classification ------- |");
        System.out.println("||------------ (Part 1) -------------||"+ "\n");
        System.out.println("Started........."+"\n\n");
    }

    /**
     * Displays END message for PART 1
     */
    public void displayPart_1_EndMessage(){

        System.out.println("| ------ Classification Done ------- |");

    }

    /**
     * Displays START message for PART 2
     */
    public void displayPart_2_StartMessage(){
        System.out.println("\n\n||-----------------------------------||");
        System.out.println("|--- Check for SEMANTIC Errors ---- |");
        System.out.println("||------------ (Part 2) -------------||"+ "\n");
        System.out.println("Started........."+"\n\n");
    }

    /**
     * Displays END message for PART 2
     */
    public void displayPart_2_EndMessage(){

        System.out.println("| ------ Checking Done ------- |");

    }

    /**
     * Displays START message for PART 3
     */
    public void displayPart_3_StartMessage(){
        System.out.println("\n\n||-----------------------------------||");
        System.out.println("|-- Fetching Books (Binary files) --- |");
        System.out.println("||------------ (Part 3) -------------||"+ "\n");
        System.out.println("Started........."+"\n\n");
    }


    /**
     * Checks every kind of possible syntax errors in the given record
     * Throws exception when syntax error detected
     * @param file
     * @param record
     * @return String array of syntax-valid book attributes from given record
     * @throws MissingFieldException
     * @throws TooFewFieldsException
     * @throws TooManyFieldsException
     * @throws UnknownGenreException
     * @throws FileNotFoundException
     */
    public String[] splitRecord(String file, String record) throws MissingFieldException,TooFewFieldsException,
            TooManyFieldsException,UnknownGenreException, FileNotFoundException{


        String[] partStrings;
        String[] content = new String[6];
        if(record.charAt(0) == '\"'){
            String newRecord = record.substring(1);
            if(newRecord.contains("\"")){
                int index = newRecord.indexOf("\"");

                if(newRecord.charAt(index+1) == ','){
                    newRecord = newRecord.substring(index+2); //After the author_name and comma(,)

                    char[] allChars = newRecord.toCharArray();
                    int commaCount = 0;
                    for(char c: allChars){
                        if(c == ',')
                            commaCount++;
                    }

                    if(commaCount>4)
                        throw new TooManyFieldsException(file, record);
                    else if(commaCount<4){
                        throw new TooFewFieldsException(file, record);
                    }

                    else{
                        partStrings = newRecord.split(",");
                        if(partStrings.length == 5 ){
                            content[0] = record.substring(0,index+2);
                            for(int i=0 ; i<partStrings.length ; i++){
                                content[i+1] = partStrings[i];
                            }

                            String missingField = "";
                            if(content[0].equals("\"\"")){
                                missingField = orderedBookAttr[0];
                                throw new MissingFieldException(file , record, missingField);

                            }
                            for(int i = 1 ; i< content.length ; i++){
                                if(content[i].trim().isEmpty()){
                                    if(missingField.trim().isEmpty()){
                                        missingField = orderedBookAttr[i];
                                    }
                                    else{
                                        missingField = missingField + " and " + orderedBookAttr[i];
                                    }

                                }

                            }

                            if(!missingField.trim().isEmpty())
                                throw new MissingFieldException(file, record, missingField);

                            //Genre Check
                            boolean match = false;
                            for(String g: genres){
                                if(content[4].equals(g))
                                    match = true;
                            }
                            if(!match)
                                throw new UnknownGenreException(file, record);


                        }//Total Data fields > 6
                        else if(partStrings.length > 5){

                            for(int i=0 ; i<content.length ; i++){
                                content[i] = partStrings[i];
                            }

                            String missingField = "";

                            for(int i = 0 ; i< content.length ; i++){
                                if(content[i].trim().isEmpty()){

                                    if(missingField.trim().isEmpty()){
                                        missingField = orderedBookAttr[i];
                                    }
                                    else{
                                        missingField = missingField + " and " + orderedBookAttr[i];
                                    }

                                }
                            }
                            throw new TooManyFieldsException(file, record, "Also missing "+ missingField);

                        }//Total Fields >6 ends

                        else { //Total Data fields < 6
                            for(int i=0 ; i<partStrings.length ; i++){
                                content[i] = partStrings[i];
                            }

                            String missingField = "";

                            for(int i = 0 ; i< partStrings.length ; i++){
                                if(content[i].trim().isEmpty()){

                                    if(missingField.trim().isEmpty()){
                                        missingField = orderedBookAttr[i];
                                    }
                                    else{
                                        missingField = missingField + " and " + orderedBookAttr[i];
                                    }

                                }
                            }
                            if(missingField.trim().isEmpty())
                                throw new MissingFieldException(file, record, orderedBookAttr[5]);
                            else
                                throw new MissingFieldException(file, record,orderedBookAttr[5] + ", "
                                        + missingField);
                        }//Total Fields <6 ends

                    }//comma count = 4 (after author) ends




                }
                else{
                    throw new TooFewFieldsException(file, record,"\",\" is missing after AUTHOR");
                }

            }
            else{
                throw new TooManyFieldsException(file, record, " (Missing 2nd Quotes)");
                //missing name
            }

        }//main IF ends

//===================================== For NO STRING =============================
        //if String does not contains any quote(s)(")
        else{

            char[] allChars = record.toCharArray();
            int commaCount = 0;
            for(char c: allChars){
                if(c == ',')
                    commaCount++;
            }

            if(commaCount>5)
                throw new TooManyFieldsException(file, record);
            else if(commaCount<5){
                throw new TooFewFieldsException(file, record);
            }
            else{

                partStrings = record.split(",");

                if(partStrings.length == 6 ){
                    for(int i=0 ; i<partStrings.length ; i++){
                        content[i] = partStrings[i];
                    }

                    String missingField = "";

                    for(int i = 0 ; i< content.length ; i++){
                        if(content[i].trim().isEmpty()){

                            if(missingField.trim().isEmpty()){
                                missingField = orderedBookAttr[i];
                            }
                            else{
                                missingField = missingField + " and " + orderedBookAttr[i];
                            }

                        }
                    }

                    if(!missingField.trim().isEmpty())
                        throw new MissingFieldException(file, record, missingField);

                    //Genre Check
                    boolean match = false;
                    for(String g: genres){
                        if(content[4].equals(g))
                            match = true;
                    }
                    if(!match){
                        throw new UnknownGenreException(file, record);
                    }


                }
                //Data fields > 6
                else if(partStrings.length > 6){

                    for(int i=0 ; i<content.length ; i++){
                        content[i] = partStrings[i];
                    }

                    String missingField = "";

                    for(int i = 0 ; i< content.length ; i++){
                        if(content[i].trim().isEmpty()){

                            if(missingField.trim().isEmpty()){
                                missingField = orderedBookAttr[i];
                            }
                            else{
                                missingField = missingField + " and " + orderedBookAttr[i];
                            }

                        }
                    }
                    throw new TooManyFieldsException(file, record, "Also missing "+ missingField);
                }//Total Fields > 6 ends

                else { //Data fields < 6

                    for(int i=0 ; i<partStrings.length ; i++){
                        content[i] = partStrings[i];
                    }

                    String missingField = "";

                    for(int i = 0 ; i< partStrings.length ; i++){
                        if(content[i].trim().isEmpty()){

                            if(missingField.trim().isEmpty()){
                                missingField = orderedBookAttr[i];
                            }
                            else{
                                missingField = missingField + " and " + orderedBookAttr[i];
                            }

                        }
                    }
                    if(missingField.trim().isEmpty())
                        throw new MissingFieldException(file, record, orderedBookAttr[5]);
                    else
                        throw new MissingFieldException(file, record,orderedBookAttr[5] + ", "
                                + missingField);
                }//Total Fields <6 ends


            }//commacount = 5 ends


        }//main ELSE ends

        return content;
    }//splitRecord() ends

    /**
     * Splits already syntax-validated records into String array
     * @param record
     * @return Book attributes in a String array
     */
    //Splitting logically valid records
    public String[] splitValidRecord(String record){

        String[] content = new String[6];

        if(record.startsWith("\"")){
            int index = record.lastIndexOf('\"');
            String newRecord = record.substring(index + 2);
            String[] partStrings = newRecord.split(",");
            content[0] = record.substring(1,index);
            for(int i = 0; i<partStrings.length; i++){
                content[i+1]= partStrings[i];
            }
        }
        else{
            content = record.split(",");
        }

        return content;
    }//splitValidRecord ends

    /**
     * Creates a book object from given details
     * @param content
     * @return a book object
     */
    public Book createBook(String[] content){

        return new Book(content[0],content[1], Double.parseDouble(content[2]),
                content[3], content[4], Integer.parseInt(content[5]));

    }




}// class Record ends