package model;

import java.time.LocalDate;

/**
 * Model representing a book borrowing transaction record.
 */
public class BorrowRecord {
    private Student student;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fine;

    public BorrowRecord(Student student, Book book, LocalDate borrowDate) {
        this.student = student;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(14); // Borrow period = 14 days
        this.returnDate = null;
        this.fine = 0.0;
    }

    // Getters and Setters
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(14);
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    @Override
    public String toString() {
        return "BorrowRecord [student=" + student.getName() + ", book=" + book.getTitle() + 
               ", borrowDate=" + borrowDate + ", dueDate=" + dueDate + 
               ", returnDate=" + returnDate + ", fine=" + fine + "]";
    }
}
