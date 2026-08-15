package main;

import model.Book;
import model.Student;
import service.LibraryService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Driver class containing the entry point and interactive menu.
 */
public class Main {
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("       Library Management System (Core Java)      ");
        System.out.println("==================================================");

        boolean running = true;
        while (running) {
            System.out.println("--- MENU ---");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Register Student");
            System.out.println("6. View Students");
            System.out.println("7. Borrow Book");
            System.out.println("8. Return Book");
            System.out.println("9. View Borrow History");
            System.out.println("10. View Reports");
            System.out.println("11. Exit");

            System.out.print("Select an option: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid option. Please choose between 1 and 11.\n");
                continue;
            }

            switch (choice) {
                case 1:
                    handleAddBook(scanner, libraryService);
                    break;
                case 2:
                    libraryService.displayBooks();
                    break;
                case 3:
                    handleSearchBook(scanner, libraryService);
                    break;
                case 4:
                    handleDeleteBook(scanner, libraryService);
                    break;
                case 5:
                    handleRegisterStudent(scanner, libraryService);
                    break;
                case 6:
                    libraryService.displayStudents();
                    break;
                case 7:
                    handleBorrowBook(scanner, libraryService);
                    break;
                case 8:
                    handleReturnBook(scanner, libraryService);
                    break;
                case 9:
                    libraryService.displayBorrowHistory();
                    break;
                case 10:
                    libraryService.displayReports();
                    break;
                case 11:
                    System.out.println("\nExiting Library Management System. Thank you!");
                    running = false;
                    break;
                default:
                    System.out.println("Error: Invalid option. Please choose between 1 and 11.\n");
            }
        }

        scanner.close();
    }

    private static void handleAddBook(Scanner scanner, LibraryService libraryService) {
        System.out.println("\n--- Add Book Details ---");
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine().trim();
        
        System.out.print("Enter Title: ");
        String title = scanner.nextLine().trim();
        
        System.out.print("Enter Author: ");
        String author = scanner.nextLine().trim();
        
        System.out.print("Enter Category: ");
        String category = scanner.nextLine().trim();
        
        System.out.print("Enter Publisher: ");
        String publisher = scanner.nextLine().trim();
        
        System.out.print("Enter Publication Year: ");
        int publicationYear;
        try {
            publicationYear = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("\nError: Publication year must be a valid number!\n");
            return;
        }
        
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine().trim();
        
        System.out.print("Enter Total Copies: ");
        int totalCopies;
        try {
            totalCopies = Integer.parseInt(scanner.nextLine().trim());
            if (totalCopies <= 0) {
                System.out.println("\nError: Total copies must be greater than 0!\n");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("\nError: Total copies must be a valid number!\n");
            return;
        }

        Book book = new Book(bookId, title, author, category, publisher, publicationYear, isbn, totalCopies);

        if (libraryService.addBook(book)) {
            System.out.println("\nSuccess: Book added successfully!\n");
        } else {
            System.out.println("Book addition failed.\n");
        }
    }

    private static void handleRegisterStudent(Scanner scanner, LibraryService libraryService) {
        System.out.println("\n--- Register Student ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Enter Branch: ");
        String branch = scanner.nextLine().trim();
        
        System.out.print("Enter Year: ");
        int year;
        try {
            year = Integer.parseInt(scanner.nextLine().trim());
            if (year <= 0) {
                System.out.println("\nError: Year must be greater than 0!\n");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("\n" + "Error: Year must be a valid number!\n");
            return;
        }
        
        System.out.print("Enter Phone (10 digits): ");
        String phone = scanner.nextLine().trim();
        if (phone.length() != 10) {
            System.out.println("\nError: Phone number must be exactly 10 digits.\n");
            return;
        }
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                System.out.println("\nError: Phone number must contain only numeric digits.\n");
                return;
            }
        }
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        if (!email.contains("@")) {
            System.out.println("\nError: Email must contain '@' symbol.\n");
            return;
        }

        Student student = new Student(studentId, name, branch, year, phone, email);

        if (libraryService.registerStudent(student)) {
            System.out.println("\nSuccess: Student registered successfully!\n");
        } else {
            System.out.println("Student registration failed.\n");
        }
    }

    private static void handleSearchBook(Scanner scanner, LibraryService libraryService) {
        System.out.println("\n--- Search Book ---");
        System.out.print("Enter search keyword (Book ID / Title / Author): ");
        String keyword = scanner.nextLine().trim();
        libraryService.searchBooks(keyword);
    }

    private static void handleDeleteBook(Scanner scanner, LibraryService libraryService) {
        System.out.println("\n--- Delete Book ---");
        System.out.print("Enter Book ID to delete: ");
        String bookId = scanner.nextLine().trim();
        if (libraryService.deleteBook(bookId)) {
            System.out.println("\nSuccess: Book with ID '" + bookId + "' deleted successfully!\n");
        } else {
            System.out.println("\nError: Book with ID '" + bookId + "' not found.\n");
        }
    }

    private static void handleBorrowBook(Scanner scanner, LibraryService libraryService) {
        System.out.println("\n--- Borrow Book ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine().trim();
        System.out.print("Enter Borrow Date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine().trim();
        
        LocalDate borrowDate;
        try {
            borrowDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            System.out.println("\nError: Invalid date format. Use YYYY-MM-DD.\n");
            return;
        }
        
        libraryService.borrowBook(studentId, bookId, borrowDate);
    }

    private static void handleReturnBook(Scanner scanner, LibraryService libraryService) {
        System.out.println("\n--- Return Book ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine().trim();
        System.out.print("Enter Return Date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine().trim();
        
        LocalDate returnDate;
        try {
            returnDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            System.out.println("\nError: Invalid date format. Use YYYY-MM-DD.\n");
            return;
        }
        
        libraryService.returnBook(studentId, bookId, returnDate);
    }
}
