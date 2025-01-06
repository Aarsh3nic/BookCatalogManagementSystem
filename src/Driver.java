// -----------------------------------------------------

// -----------------------------------------------------





import ExceptionClasses.*;
import java.util.Scanner;
import java.io.*;

/**
 * Driver Class to run all the coded methods and function the program.
 * @author Aarsh Patel
 */
public class Driver {

    public static void main(String[] args){

        int[] numOfRecords ; // To keep the record count

        do_part_1();
        numOfRecords = do_part_2();
        do_part_3(numOfRecords);

    }//main() ends

    /**
     * Creates Directory part_1
     * part_1 contains all the genre classified book TEXT FILES and Syntax error logged file
     */
    public static void do_part_1(){

        Record record = new Record();
        int[] numOfRecords = new int[8];


        try{

            // Process notifier message
            record.displayPart_1_StartMessage();
            // Getting non-empty file names
            String[] all_files = record.getFileNames("book_dir/part1_input_file_names.txt");
            String[] lineContent; // To Store record's contents
            String recordLine; // To store record(line)

            //checking every FILE
            for(String file : all_files){


                Scanner scan = new Scanner(new FileInputStream("book_dir/"+file));

                try(PrintWriter pwComics = new PrintWriter(new FileOutputStream("part_1/Cartoons_Comics.csv",true));
                    PrintWriter pwHobbies = new PrintWriter(new FileOutputStream("part_1/Hobbies_Collectibles.csv",true));
                    PrintWriter pwMovies_TV = new PrintWriter(new FileOutputStream("part_1/Movies_TV_Books.csv",true));
                    PrintWriter pwMusic = new PrintWriter(new FileOutputStream("part_1/Music_Radio_Books.csv",true));
                    PrintWriter pwNostalgia = new PrintWriter(new FileOutputStream("part_1/Nostalgia_Eclectic_Books.csv",true));
                    PrintWriter pwRadio = new PrintWriter(new FileOutputStream("part_1/Old_Time_Radio_Books.csv",true));
                    PrintWriter pwSports = new PrintWriter(new FileOutputStream("part_1/Sports_Sports_Memorabilia.csv",true));
                    PrintWriter pwAutoMobiles = new PrintWriter(new FileOutputStream("part_1/Trains_Planes_Automobiles.csv",true));
                    PrintWriter pwError = new PrintWriter(new FileOutputStream("part_1/syntax_error_file.txt",true))
                    ){

                    //Creating a title for Every file errors
                    pwError.println("Syntax error in file: " + file);
                    pwError.println("========================================");

                    while(scan.hasNextLine()){

                        //checking every record
                        try{
                            recordLine = scan.nextLine();
                            lineContent = record.splitRecord(file, recordLine);

                            //Classifying every record according its genre
                            switch (lineContent[4]){

                                case "CCB":
                                    pwComics.println(recordLine);
                                    numOfRecords[0]++;
                                    break;

                                case "HCB":
                                    pwHobbies.println(recordLine);
                                    numOfRecords[1]++;
                                    break;

                                case "MTV":
                                    pwMovies_TV.println(recordLine);
                                    numOfRecords[2]++;
                                    break;

                                case "MRB":
                                    pwMusic.println(recordLine);
                                    numOfRecords[3]++;
                                    break;

                                case "NEB":
                                    pwNostalgia.println(recordLine);
                                    numOfRecords[4]++;
                                    break;

                                case "OTR":
                                    pwRadio.println(recordLine);
                                    numOfRecords[5]++;
                                    break;

                                case "SSM":
                                    pwSports.println(recordLine);
                                    numOfRecords[6]++;
                                    break;

                                case "TPA":
                                    pwAutoMobiles.println(recordLine);
                                    numOfRecords[7]++;
                                    break;

                            }

                        }//RECORD-try block ends
                        catch(TooFewFieldsException e){

                                pwError.println("Error: "+ e.getError());
                                pwError.println("Record: " + e.getRecord());
                                pwError.println("");
                        }
                        catch(TooManyFieldsException e){

                            pwError.println("Error: "+ e.getError());
                            pwError.println("Record: " + e.getRecord());
                            pwError.println("");
                        }
                        catch(UnknownGenreException e){

                            pwError.println("Error: "+ e.getERROR());
                            pwError.println("Record: " + e.getRecord());
                            pwError.println("");
                        }
                        catch(MissingFieldException e){

                            pwError.println("Error: "+ e.getError());
                            pwError.println("Record: " + e.getRecord());
                            pwError.println("");
                        }
                        catch(Exception e){
                            System.out.println("Unknown Exception occurred");
                        }//RECORD-catch block ends

                    }

                    //Creating an END for Every file errors
                    pwError.println("------------xx------------xx-------------");
                    pwError.println("");


                }//FILE-try block ends
                catch(FileNotFoundException e){
                    System.out.println("File: "+ file + " cannot be found.");
                }//FILE-catch block ends


            }//for loop ends


        }
        catch(FileNotFoundException e){
            System.out.println("File cannot be opened");
        }

        record.displayPart_1_EndMessage(); // Process notifier message

    }//do_part_1() ends

    /**
     * Creates Directory part_2
     * part_2 contains all the genre classified book BINARY FILES and Semantic error logged file
     * All BINARY FILES contains book objects
     */
    public static int[] do_part_2(){

        int[] numOfRecords = new int[8];
        Record record = new Record();
        record.displayPart_2_StartMessage(); // Process notifier message

        String[] all_files = {"part_1/Cartoons_Comics.csv", "part_1/Hobbies_Collectibles.csv",
                "part_1/Movies_TV_Books.csv","part_1/Music_Radio_Books.csv", "part_1/Nostalgia_Eclectic_Books.csv",
                "part_1/Old_Time_Radio_Books.csv", "part_1/Sports_Sports_Memorabilia.csv",
                "part_1/Trains_Planes_Automobiles.csv"};

        String[] lineContent;
        String recordLine;
        //checking every FILE -------------------------------------------------------

        int index=0; //index for files
        int recordCounter;
        for(String file : all_files){


            recordCounter = 0; //resets every file

            try(Scanner scan = new Scanner(new FileInputStream(file));
                ObjectOutputStream oos =
                        new ObjectOutputStream(
                                new FileOutputStream("part_2/"+file.substring(7)+".ser", true));
                PrintWriter pwError =
                        new PrintWriter(new FileOutputStream("part_2/semantic_error_file.txt",true))){

                //Creating a title for Every file errors
                pwError.println("Semantic error in file: " + file.substring(7));
                pwError.println("========================================");

                while(scan.hasNextLine()){

                    //checking every record
                    try{
                        recordLine = scan.nextLine();
                        lineContent =  record.splitValidRecord(recordLine);
                        Book book = record.createBook(lineContent);
                        book.isValid(file.substring(7), recordLine);
                        //System.out.println(book);
                        oos.writeObject(book);
                        recordCounter++;

                    }//RECORD-try block ends
                    catch(BadYearException e){

                        pwError.println("Error: "+ e.getError());
                        pwError.println("Record: " + e.getRecord());
                        pwError.println("");
                    }
                    catch(BadPriceException e){

                        pwError.println("Error: "+ e.getError());
                        pwError.println("Record: " + e.getRecord());
                        pwError.println("");
                    }
                    catch(BadIsbn10Exception e){

                        pwError.println("Error: "+ e.getError());
                        pwError.println("Record: " + e.getRecord());
                        pwError.println("");
                    }
                    catch(BadIsbn13Exception e){

                        pwError.println("Error: "+ e.getError());
                        pwError.println("Record: " + e.getRecord());
                        pwError.println("");
                    }
                    catch(Exception e){
                        System.out.println("Unknown Exception occurred");
                    }//RECORD-catch block ends

                    finally {

                    }
                }

                //Creating an END for Every file errors
                pwError.println("------------xx------------xx-------------");
                pwError.println("");


            }//FILE-try block ends
            catch(IOException e){
                System.out.println("File: "+ file + " cannot be found.");
            }//FILE-catch block ends

            numOfRecords[index] = recordCounter;
            index ++;
        }

        record.displayPart_2_EndMessage(); // Process notifier message

        return numOfRecords;
    }//do_part_2() ends

    /**
     * Fetches book objects from BINARY FILES and stores in 2D array
     * Displays those books as per demand of the user
     * Let user decide the number of record that he/she want to browse
     */
    public static void do_part_3(int[] numOfRecords) {

        Record record = new Record();
        record.displayPart_3_StartMessage(); // Process notifier message

        //Creating Book arrays
        Book[][] books = new Book[8][];

        //Assigning Storage capacity
        for(int i=0; i<numOfRecords.length;i++){
            books[i] = new Book[numOfRecords[i]];
        }


        String[] all_files = {"Cartoons_Comics.csv.ser", "Hobbies_Collectibles.csv.ser",
                "Movies_TV_Books.csv.ser", "Music_Radio_Books.csv.ser", "Nostalgia_Eclectic_Books.csv.ser",
                "Old_Time_Radio_Books.csv.ser", "Sports_Sports_Memorabilia.csv.ser",
                "Trains_Planes_Automobiles.csv.ser"};

        int recordCounter;//iterates in book loop

        int index = 0; //index for files

        //Fetching from every file
        for (String file : all_files) {

            recordCounter = 0; //resets every file

            try (ObjectInputStream oos = new ObjectInputStream
                    (new FileInputStream("part_2/" + file));) {

                while (recordCounter<numOfRecords[index]) {

                    //checking every record
                    try {
                        books[index][recordCounter] = (Book)oos.readObject();

                    }//RECORD-try block ends
                    catch (EOFException e) {
                        System.out.println("End of the file Reached");
                    }
                     catch (Exception e) {
                        System.out.println("Unknown Exception occurred");
                    }//RECORD-catch blocks ends
                    finally {
                        recordCounter++;
                    }//RECORD-finally block ends

                }//while loop ends

            }//FILE-try block ends

            catch(IOException e){
                System.out.println("File: " + file + " cannot be found.");
            }//FILE-catch block ends
            finally{
                index ++;
            }//FILE-finally block ends

        }//for loop ends


        //================================ DISPLAY - Starts ===============================

        Menu menu = new Menu();

        int fileIndex = 0;
        int[][] recordIndex = new int[8][1];
        boolean openView;
        int userChoice;
        int subMenuChoice;
        //setting default value for all current-record indexes
        for(int[] i : recordIndex){
            i[0] = 0;
        }

        while(true){

            menu.displayMainMenu(all_files, fileIndex,numOfRecords );

            switch(menu.getMenuChoice()){

                case 'v':
                    openView = true;
                    System.out.println("viewing: " +
                            all_files[fileIndex] + "  ("+numOfRecords[fileIndex]+ " records)");
                    System.out.println("\n(Enter 0 to quit the VIEW MENU)");
                    while(openView){
                        userChoice = menu.getViewChoice();
                        if(userChoice == 0)
                            openView = false;

                        recordIndex[fileIndex][0] = menu.displayBooks(recordIndex[fileIndex][0],
                                userChoice, fileIndex,books);
                    }
                    break;

                case 's':
                    menu.displaySubMenu(all_files,numOfRecords);
                    subMenuChoice = menu.getSubMenuChoice();

                    if(subMenuChoice !=8 ) //if option 9 is selected in Sub-Menu
                        fileIndex = subMenuChoice;
                    else
                        System.out.println("\nQuitting VIEWING Menu... \n");

                    break;

                case 'x':
                    menu.displayEndProgMessage();
                    System.exit(0);
                    break;
            }


        }

        //================================ DISPLAY- Ends ===============================

    }//do_part_3() ends





}// class Driver ends
