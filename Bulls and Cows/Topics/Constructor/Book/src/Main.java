import java.util.Arrays;

class Book {
    String title;
    int yearOfPublishing;
    String[] authors;

    Book(String title, int yearOfPublishing, String[] authors) {
        this.title = title;
        this.yearOfPublishing = yearOfPublishing;
        this.authors = new String[authors.length];
        /*
         *  Instead of assigning the array directly, it is good to make a copy
         *  and assign it to new object.
         */
        this.authors = Arrays.copyOf(authors, authors.length);
    }
}