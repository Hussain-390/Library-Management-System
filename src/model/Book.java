package model;

/**
 * Model representing a book in the library system.
 */
public class Book {
    private String bookId;
    private String title;
    private String author;
    private String category;
    private String publisher;
    private int publicationYear;
    private String isbn;
    private int totalCopies;
    private int availableCopies;

    public Book(String bookId, String title, String author, String category, 
                String publisher, int publicationYear, String isbn, int totalCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    // Getters and Setters
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Override
    public String toString() {
        return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + 
               ", category=" + category + ", publisher=" + publisher + 
               ", publicationYear=" + publicationYear + ", isbn=" + isbn + 
               ", totalCopies=" + totalCopies + ", availableCopies=" + availableCopies + "]";
    }
}
