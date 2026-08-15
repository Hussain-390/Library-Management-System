package service;

import model.Book;
import model.Student;
import model.BorrowRecord;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class managing memory collections for books, students, and checkouts.
 */
public class LibraryService {
    private final List<Book> books = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();
    private final List<BorrowRecord> borrowHistory = new ArrayList<>();
    // bookIndex maps lower-cased Book ID -> Book object for O(1) lookup by ID
    private final Map<String, Book> bookIndex = new HashMap<>();

    /**
     * Adds a book to the library system. Prevents duplicate Book IDs.
     * Uses bookIndex for O(1) duplicate check.
     */
    public boolean addBook(Book book) {
        String key = book.getBookId().trim().toLowerCase();
        if (bookIndex.containsKey(key)) {
            System.out.println("\nError: Book with ID '" + book.getBookId() + "' already exists.");
            return false;
        }
        books.add(book);
        bookIndex.put(key, book);
        return true;
    }

    /**
     * Displays all books in a formatted console table.
     */
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("\nNo books exist in the library system yet.");
            return;
        }
        System.out.println("\n=================================== LIBRARY BOOKS ===================================");
        System.out.printf("| %-8s | %-20s | %-15s | %-12s | %-12s | %-6s | %-12s | %-5s | %-5s |\n", 
            "Book ID", "Title", "Author", "Category", "Publisher", "Year", "ISBN", "Total", "Avail");
        System.out.println("+----------+----------------------+-----------------+--------------+--------------+------+------------+-------+-------+");
        for (Book book : books) {
            System.out.printf("| %-8s | %-20s | %-15s | %-12s | %-12s | %-6d | %-12s | %-5d | %-5d |\n",
                truncate(book.getBookId(), 8),
                truncate(book.getTitle(), 20),
                truncate(book.getAuthor(), 15),
                truncate(book.getCategory(), 12),
                truncate(book.getPublisher(), 12),
                book.getPublicationYear(),
                truncate(book.getIsbn(), 12),
                book.getTotalCopies(),
                book.getAvailableCopies());
        }
        System.out.println("=====================================================================================");
    }

    /**
     * Searches for books matching a keyword in Book ID, Title, or Author.
     */
    public void searchBooks(String keyword) {
        List<Book> matchingBooks = new ArrayList<>();
        String query = keyword.toLowerCase().trim();
        for (Book book : books) {
            if (book.getBookId().toLowerCase().contains(query) ||
                book.getTitle().toLowerCase().contains(query) ||
                book.getAuthor().toLowerCase().contains(query)) {
                matchingBooks.add(book);
            }
        }

        if (matchingBooks.isEmpty()) {
            System.out.println("\nNo matching books found for search keyword: '" + keyword + "'");
            return;
        }

        System.out.println("\n=================================== MATCHING BOOKS ==================================");
        System.out.printf("| %-8s | %-20s | %-15s | %-12s | %-12s | %-6s | %-12s | %-5s | %-5s |\n", 
            "Book ID", "Title", "Author", "Category", "Publisher", "Year", "ISBN", "Total", "Avail");
        System.out.println("+----------+----------------------+-----------------+--------------+--------------+------+------------+-------+-------+");
        for (Book book : matchingBooks) {
            System.out.printf("| %-8s | %-20s | %-15s | %-12s | %-12s | %-6d | %-12s | %-5d | %-5d |\n",
                truncate(book.getBookId(), 8),
                truncate(book.getTitle(), 20),
                truncate(book.getAuthor(), 15),
                truncate(book.getCategory(), 12),
                truncate(book.getPublisher(), 12),
                book.getPublicationYear(),
                truncate(book.getIsbn(), 12),
                book.getTotalCopies(),
                book.getAvailableCopies());
        }
        System.out.println("=====================================================================================");
    }

    /**
     * Deletes a book by ID.
     * Removes the entry from bookIndex to keep it in sync.
     */
    public boolean deleteBook(String bookId) {
        String key = bookId.trim().toLowerCase();
        Book book = bookIndex.get(key);
        if (book == null) {
            return false;
        }
        books.remove(book);
        bookIndex.remove(key);
        return true;
    }

    /**
     * Registers a new student. Prevents duplicate Student IDs.
     */
    public boolean registerStudent(Student student) {
        for (Student s : students) {
            if (s.getStudentId().equalsIgnoreCase(student.getStudentId().trim())) {
                System.out.println("\nError: Student with ID '" + student.getStudentId() + "' already exists.");
                return false;
            }
        }
        students.add(student);
        return true;
    }

    /**
     * Displays registered students in a formatted table.
     */
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("\nNo students registered in the system yet.");
            return;
        }
        System.out.println("\n======================================= REGISTERED STUDENTS =======================================");
        System.out.printf("| %-12s | %-20s | %-15s | %-5s | %-12s | %-25s |\n", 
            "Student ID", "Name", "Branch", "Year", "Phone", "Email");
        System.out.println("+--------------+----------------------+-----------------+-------+--------------+---------------------------+");
        for (Student student : students) {
            System.out.printf("| %-12s | %-20s | %-15s | %-5d | %-12s | %-25s |\n",
                truncate(student.getStudentId(), 12),
                truncate(student.getName(), 20),
                truncate(student.getBranch(), 15),
                student.getYear(),
                truncate(student.getPhone(), 12),
                truncate(student.getEmail(), 25));
        }
        System.out.println("====================================================================================================");
    }

    /**
     * Checks out a book to a student.
     */
    public void borrowBook(String studentId, String bookId, LocalDate borrowDate) {
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("\nError: Student with ID '" + studentId + "' not found.");
            return;
        }

        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("\nError: Book with ID '" + bookId + "' not found.");
            return;
        }

        if (book.getAvailableCopies() <= 0) {
            System.out.println("\nError: No copies of book '" + book.getTitle() + "' are currently available.");
            return;
        }

        // Check double borrowing
        for (BorrowRecord record : borrowHistory) {
            if (record.getStudent().getStudentId().equalsIgnoreCase(studentId.trim()) &&
                record.getBook().getBookId().equalsIgnoreCase(bookId.trim()) &&
                record.getReturnDate() == null) {
                System.out.println("\nError: Student has already borrowed this book and has not returned it yet.");
                return;
            }
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        BorrowRecord record = new BorrowRecord(student, book, borrowDate);
        borrowHistory.add(record);

        System.out.println("\nSuccess: Book '" + book.getTitle() + "' borrowed successfully by " + student.getName() + "!");
        System.out.println("Due date: " + record.getDueDate() + "\n");
    }

    /**
     * Returns a book, calculating overdue fees (₹10/day after 14 days limit).
     */
    public void returnBook(String studentId, String bookId, LocalDate returnDate) {
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("\nError: Student with ID '" + studentId + "' not found.");
            return;
        }

        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("\nError: Book with ID '" + bookId + "' not found.");
            return;
        }

        BorrowRecord activeRecord = null;
        for (BorrowRecord record : borrowHistory) {
            if (record.getStudent().getStudentId().equalsIgnoreCase(studentId.trim()) &&
                record.getBook().getBookId().equalsIgnoreCase(bookId.trim()) &&
                record.getReturnDate() == null) {
                activeRecord = record;
                break;
            }
        }

        if (activeRecord == null) {
            System.out.println("\nError: No active borrow transaction found for student '" + student.getName() + "' and book '" + book.getTitle() + "'.");
            return;
        }

        if (returnDate.isBefore(activeRecord.getBorrowDate())) {
            System.out.println("\nError: Return date cannot be before borrow date (" + activeRecord.getBorrowDate() + ").");
            return;
        }

        // Calculate late fine
        double fine = 0.0;
        LocalDate dueDate = activeRecord.getDueDate();
        if (returnDate.isAfter(dueDate)) {
            long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
            fine = daysLate * 10.0; // ₹10 per day late fee
        }

        activeRecord.setReturnDate(returnDate);
        activeRecord.setFine(fine);
        book.setAvailableCopies(book.getAvailableCopies() + 1);

        System.out.println("\nSuccess: Book '" + book.getTitle() + "' returned successfully!");
        if (fine > 0) {
            System.out.printf("Late Return Fee: Rs. %.2f (Due Date was %s, Returned on %s)\n\n", fine, dueDate, returnDate);
        } else {
            System.out.println("Returned on time. No fine.\n");
        }
    }

    /**
     * Displays transaction borrow/return logs.
     */
    public void displayBorrowHistory() {
        if (borrowHistory.isEmpty()) {
            System.out.println("\nNo borrow history records exist yet.");
            return;
        }
        System.out.println("\n=================================== BORROW HISTORY ===================================");
        System.out.printf("| %-15s | %-20s | %-12s | %-12s | %-12s | %-10s |\n", 
            "Student Name", "Book Name", "Borrow Date", "Due Date", "Return Date", "Fine");
        System.out.println("+-----------------+----------------------+--------------+--------------+--------------+------------+");
        for (BorrowRecord record : borrowHistory) {
            String returnDateStr = record.getReturnDate() == null ? "Not Returned" : record.getReturnDate().toString();
            String fineStr = String.format("Rs. %.2f", record.getFine());
            System.out.printf("| %-15s | %-20s | %-12s | %-12s | %-12s | %-10s |\n",
                truncate(record.getStudent().getName(), 15),
                truncate(record.getBook().getTitle(), 20),
                record.getBorrowDate().toString(),
                record.getDueDate().toString(),
                returnDateStr,
                fineStr);
        }
        System.out.println("=======================================================================================");
    }

    /**
     * Displays summary statistics reports.
     */
    public void displayReports() {
        int totalUniqueBooks = books.size();
        int totalCopies = 0;
        int availableCopies = 0;
        for (Book book : books) {
            totalCopies += book.getTotalCopies();
            availableCopies += book.getAvailableCopies();
        }

        int totalStudents = students.size();
        int totalHistoryRecords = borrowHistory.size();
        int activeBorrowedCopies = 0;
        int overdueCopies = 0;
        LocalDate today = LocalDate.now();

        for (BorrowRecord record : borrowHistory) {
            if (record.getReturnDate() == null) {
                activeBorrowedCopies++;
                if (today.isAfter(record.getDueDate())) {
                    overdueCopies++;
                }
            }
        }

        Book mostBorrowedBook = null;
        int maxBorrows = 0;
        Map<String, Integer> borrowCounts = new HashMap<>();

        for (BorrowRecord record : borrowHistory) {
            Book book = record.getBook();
            String id = book.getBookId();
            int count = borrowCounts.getOrDefault(id, 0) + 1;
            borrowCounts.put(id, count);

            if (count > maxBorrows) {
                maxBorrows = count;
                mostBorrowedBook = book;
            }
        }

        String mostBorrowedStr = "N/A";
        if (mostBorrowedBook != null) {
            mostBorrowedStr = String.format("%s (by %s) [%d times]", 
                mostBorrowedBook.getTitle(), mostBorrowedBook.getAuthor(), maxBorrows);
        }

        System.out.println("\n=================================== LIBRARY REPORT ===================================");
        System.out.printf("| %-42s : %-37s |\n", "Total Book Titles (Unique)", String.valueOf(totalUniqueBooks));
        System.out.printf("| %-42s : %-37s |\n", "Total Book Copies", String.valueOf(totalCopies));
        System.out.printf("| %-42s : %-37s |\n", "Available Book Copies", String.valueOf(availableCopies));
        System.out.printf("| %-42s : %-37s |\n", "Total Borrowed Book Copies (Active)", String.valueOf(activeBorrowedCopies));
        System.out.printf("| %-42s : %-37s |\n", "Total Overdue Books (Active)", String.valueOf(overdueCopies));
        System.out.printf("| %-42s : %-37s |\n", "Total Registered Students", String.valueOf(totalStudents));
        System.out.printf("| %-42s : %-37s |\n", "Total Historical Checkouts", String.valueOf(totalHistoryRecords));
        System.out.printf("| %-42s : %-37s |\n", "Most Borrowed Book", mostBorrowedStr);
        System.out.println("======================================================================================");
    }

    private Student findStudentById(String studentId) {
        for (Student s : students) {
            if (s.getStudentId().equalsIgnoreCase(studentId.trim())) {
                return s;
            }
        }
        return null;
    }

    /**
     * Looks up a book by ID in O(1) using the bookIndex HashMap.
     */
    private Book findBookById(String bookId) {
        return bookIndex.get(bookId.trim().toLowerCase());
    }

    private String truncate(String text, int limit) {
        if (text == null) return "";
        if (text.length() <= limit) return text;
        return text.substring(0, limit - 2) + "..";
    }
}
