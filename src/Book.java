
import ExceptionClasses.BadIsbn10Exception;
import ExceptionClasses.BadIsbn13Exception;
import ExceptionClasses.BadPriceException;
import ExceptionClasses.BadYearException;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represent book in records
 * Book can have 6 attributes : Title, Author, Price, ISBN, Genre and Year
 * @author Aarsh Patel
 */

public class Book implements Serializable {

//title, authors, price, isbn, genre, year
    private String title;
    private String authors;
    private double price;
    private String isbn;
    private String genre;
    private int year;

    //------------------ Constructors ------------------//

    public Book() {
        this.title = "";
        this.authors = "";
        this.price = 0d;
        this.isbn = "";
        this.genre = "";
        this.year = 0;
    }
    public Book(String title, String authors, double price, String isbn, String genre, int year) {
        this.title = title;
        this.authors = authors;
        this.price = price;
        this.isbn = isbn;
        this.genre = genre;
        this.year = year;
    }




    //------------------ Getters and Setters ------------------//

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


  @Override
    public boolean equals(Object x) {

        if(x == null || this.getClass() != x.getClass()){
            return false;
        }

        Book book = (Book)x;

        return(this.title.equals(book.title) &&
                this.authors.equals(book.authors) &&
                this.isbn.equals(book.isbn) &&
                this.genre.equals(book.genre) &&
                this.price == book.price &&
                this.year == book.year);
    }//equals() ends



    @Override
    public String toString() {
        return "Book {" +
                "Title = '" + title + '\'' +
                ", Authors = '" + authors + '\'' +
                ", Price = $" + price +
                ", ISBN = '" + isbn + '\'' +
                ", Genre = '" + genre + '\'' +
                ", Year = " + year +
                "}";
    }//toString() ends


    //===============================   Other Methods   ========================================//

    /**
     * Checks if the book object is sematically valid
     * validates isbn, year and price
     */
    public void isValid(String file, String record) throws BadYearException, BadPriceException, BadIsbn13Exception, BadIsbn10Exception {

        //Checking Year
        if(!(this.year>=1995 && this.year<=2010))
            throw new BadYearException(file, record);

        //Checking Price
        if(this.price<0)
            throw new BadPriceException(file, record);

        //Checking ISBN
        int sum = 0;
        boolean isDigit = true;
        char[] chs = isbn.toCharArray();
        for(char c : chs){
            if((int)c <48 || (int)c >57)
                isDigit=false;
        }

        //For 10-Digit-ISBN
        if(this.isbn.length() ==10){

            if(isDigit){
                String[] num = isbn.split("");
                int[] digits = new int[num.length];
                for(int i=0; i< digits.length; i++){
                    digits[i] = Integer.parseInt(num[i]);
                }
                for(int i = 0,j =10; i<digits.length; i++,j--){
                    sum = sum + j*digits[i];
                }

                if(sum % 11 !=0)
                    throw new BadIsbn10Exception(file, record);

            }else{
                throw new BadIsbn10Exception(file, record);
            }

        }

        //For 13-Digit-ISBN
        else if (this.isbn.length() ==13) {

            if(isDigit){
                String[] num = isbn.split("");
                int[] digits = new int[num.length];
                for(int i=0; i< digits.length; i++){
                    digits[i] = Integer.parseInt(num[i]);
                }
                for(int i = 0; i<digits.length; i++){

                    if((i+1)%2 == 0)
                        sum = sum + 3*digits[i];
                    else
                        sum = sum + digits[i];
                }
                if(sum % 10 !=0)
                    throw new BadIsbn13Exception(file, record);

            }
            else{
                throw new BadIsbn13Exception(file, record);
            }

        }
        //For Invalid-Digit-ISBN
        else{
            throw new BadIsbn13Exception(file, record, "(Invalid number of digits)");
        }


    }//isValid() ends


}// class Book ends
