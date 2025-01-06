

import java.util.Scanner;

/**
 * Menu class contains all the methods needed for formating the display of the book records.
 * @author Aarsh Patel
 */
public class Menu {

    Scanner scan = new Scanner(System.in);
    /**
     * Gets a valid input from user to display as many records
     * @return entered integer
     */
    public int getViewChoice(){
        boolean invalid = true;
        int choice = 0;
        while(invalid){
            try{

                System.out.print("\n-->(View)Enter number of records: ");
                choice = scan.nextInt();
                scan.nextLine(); //to flush out the line
                invalid= false;

            }catch(Exception e){
                System.out.println("\nInvalid Choice , try again...");
                scan.nextLine(); //to flush out the line
            }
        }

        //scan.close();
        return choice;
    }//getViewChoice() ends




    /**
     * Gets a valid input from user to display the right file records
     * @return entered integer
     */
    public int getSubMenuChoice(){
        boolean invalid = true;
        int choice = 0;
        while(invalid){
            try{

                System.out.print("Enter Your Choice: ");
                choice = scan.nextInt();
                scan.nextLine(); //to flush out the line

                if(choice >= 1 && choice<=9)
                    invalid= false;
                else
                    System.out.println("\nChoose between (1-9)...");

            }catch(Exception e){
                System.out.println("\nInvalid Choice, try again...");
                scan.nextLine(); //to flush out the line
            }
        }

        //scan.close();

        return choice -1;
    }//getSubMenuChoice() ends

    /**
     * Gets a valid input from user to display right sub-menu or exit
     * @return entered integer
     */
    public char getMenuChoice(){
        boolean invalid = true;
        char choice = 'f';
        char[] input;
        while(invalid){
            try{

                System.out.print("Enter Your Choice: ");
                input = scan.nextLine().trim().toLowerCase().toCharArray();
                if(input.length == 1){
                    if(input[0] == 'v'|| input[0] == 's' || input[0] == 'x'){
                        choice = input[0];
                        invalid = false;
                    }
                }
                if(invalid)
                    System.out.println("\nInvalid Choice, try again...");

            }catch(Exception e){
                System.out.println("\nInvalid Choice, try again...");
            }
        }

        //scan.close();

        return choice;
    }//getMenuChoice() ends




    /**
     * Displays END message for PART 3 and the program itself
     */
    public void displayEndProgMessage() {
        System.out.println("\nProgram ending...");
        System.out.println("     Thank you for using this Program, Bye!");
        System.out.println("========================= END ============================");

    }


    /**
     * Displays the MAIN MENU
     * @param all_files
     * @param fileIndex
     * @param numOfRecords
     */
    public void displayMainMenu(String[] all_files, int fileIndex, int[] numOfRecords) {

        System.out.println("==========================================================");
        System.out.println("------------------------ Main Menu -----------------------");
        System.out.println("==========================================================");
        System.out.println("|v| View the selected file: " + all_files[fileIndex] +
                " (" + numOfRecords[fileIndex] + " records)");
        System.out.println("|s| Select a file to view");
        System.out.println("|x| Exit");
        System.out.println("==========================================================");
    }//displayMainMenu() ends

    /**
     * Displays the SUB-MENU
     * @param all_files
     * @param numOfRecords
     */
    public void displaySubMenu(String[] all_files, int[] numOfRecords){
        System.out.println("==========================================================");
        System.out.println("-------------------- File Sub-Menu -----------------------");
        System.out.println("==========================================================");
        for (int i = 0; i < all_files.length; i++) {
            System.out.printf("%-3d %-35s (%d records)%n", i + 1, all_files[i], numOfRecords[i]);
        }
        System.out.println("9   Exit");
        System.out.println("==========================================================");

    }//displaySubMenu ends


    /**
     * Calculates the number of books that should be displayed according to user's input
     * @param index
     * @param n
     * @param fileIndex
     * @param books
     * @return the Current index of book that the user is at or was for the file record
     */
    public int displayBooks(int index, int n, int fileIndex, Book[][] books){

        //REMOVE if 0 cant be placed
        if(n==0){
            System.out.println("\nQuitting VIEWING Menu... \n");
            return index;
        }


        if(n>0){
            System.out.println("\n --Records--");//for good formatting
            for (int i = index; i < index + n; i++) {
                System.out.println(books[fileIndex][i]);

                if(i == (books[fileIndex].length-1)){
                    System.out.println("-----EOF has been reached---- ");
                    return i;
                }

            }

            return index + (n-1);
        }
        //when  n<0
        n = n*(-1);// to get the |n| (absolute) value
        System.out.println("\n --Records--");//for good formatting
        for (int i = index; i > index - n; i--) {
            System.out.println(books[fileIndex][i]);

            if(i == 0){
                System.out.println("\n-----BOF has been reached---- \n");
                return i;
            }
        }

        return index + (n-1);


    }//displayBooks() ends

}//class Menu ends
