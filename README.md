# 📚 Console-Based Library Management System

> A fully functional Library Management System built exclusively with **Core Java**, demonstrating fundamental Java programming concepts, Object-Oriented Design, Collections Framework, Java Time API, and real-world console application development patterns.

---

## 1. 🎯 Project Overview

### Problem Statement
Libraries need a system to manage books, students, and book transactions. This project solves that problem by providing a console-based application capable of managing the complete lifecycle of library operations — from adding books and registering students, to tracking borrowing transactions and generating statistical reports.

### Why Core Java?
This project uses **only Core Java** (no Spring Boot, no databases, no external libraries) intentionally. The goal is to demonstrate a deep understanding of Java fundamentals — the very skills assessed in fresher and junior developer interviews. When you can solve a real-world problem using nothing but the Java standard library, it proves you understand the language itself, not just the frameworks built on top of it.

### Skills Demonstrated
- Object-Oriented Programming (Encapsulation, Abstraction)
- Java Collections Framework (`ArrayList`, `HashMap`, `List`, `Map`)
- Java Time API (`LocalDate`, `ChronoUnit`)
- Console I/O with `Scanner`
- Input Validation and defensive programming
- Layered architecture design
- Business logic separation from UI logic
- HashMap indexing for O(1) book lookup by ID

### Why It Is Suitable for Fresher Interviews
This project covers practically every Core Java concept taught in a Bachelor's degree curriculum, implemented in a cohesive, real-world scenario. It gives you confident answers to questions like:
- *"Which collection did you use and why?"*
- *"How did you handle invalid inputs?"*
- *"Explain encapsulation with an example from your project."*
- *"Why did you use a HashMap alongside your ArrayList?"*

---

## 2. 🏗️ Project Architecture

### Directory Structure

```
Library Management System/
│
├── src/
│   ├── model/
│   │   ├── Book.java           # Data entity for a library book
│   │   ├── Student.java        # Data entity for a student
│   │   └── BorrowRecord.java   # Data entity for a borrowing transaction
│   │
│   ├── service/
│   │   └── LibraryService.java # All business logic and operations
│   │
│   └── main/
│       └── Main.java           # Entry point, CLI menu, Scanner input
│
├── bin/                        # Compiled .class bytecode (auto-generated)
├── README.md                   # Project documentation
└── .gitignore                  # Git ignored files (bin/, *.dat, IDE configs)
```

### Package Responsibilities

| Package | Responsibility |
|---------|----------------|
| `model` | Plain Java classes (POJOs) holding data fields with getters and setters. No logic. |
| `service` | All business rules: validation, searching, sorting, calculation, and reporting. |
| `main` | CLI interaction only: Scanner input, menu loop, and calling service methods. |

### Why This Layered Architecture?
Even for a small console application, this separation demonstrates an understanding of clean code principles:
- **Separation of Concerns**: The UI layer (`Main`) does not contain business logic. The service layer does not handle Scanner I/O.
- **Maintainability**: If you later want to add a GUI or REST API, you only replace `Main.java`. The business logic and models remain unchanged.
- **Testability**: `LibraryService` can be independently tested because it has no dependency on `Scanner`.
- **Interview Value**: It shows you can architect a project, not just write code.

---

## 3. 🔄 Application Workflow

```
┌─────────────────────────────────────────┐
│             JVM starts Main.java         │
└───────────────────┬─────────────────────┘
                    │
          ┌─────────▼──────────┐
          │  Display Menu      │
          │  (1-11 options)    │
          └─────────┬──────────┘
                    │
          ┌─────────▼──────────┐
          │  Scanner reads     │
          │  user input        │
          └─────────┬──────────┘
                    │
          ┌─────────▼──────────┐
          │  switch(choice)    │◄── Invalid input → Error message → loop back
          └─────────┬──────────┘
                    │
          ┌─────────▼──────────┐
          │  Handle method     │
          │  in Main.java      │
          └─────────┬──────────┘
                    │
          ┌─────────▼──────────┐
          │  LibraryService    │
          │  executes logic    │
          └─────────┬──────────┘
                    │
          ┌─────────▼──────────┐
          │  Model objects     │
          │  (Book/Student/    │
          │   BorrowRecord)    │
          └─────────┬──────────┘
                    │
          ┌─────────▼──────────┐
          │  Output displayed  │
          │  in formatted grid │
          └─────────┬──────────┘
                    │
          ┌─────────▼──────────┐
          │ Choice == 11?      │──── YES ──► Exit JVM
          └─────────┬──────────┘
                   NO
                    │
                    └──────────► Loop back to Display Menu
```

---

## 4. ☕ Core Java Concepts Used

| Concept | Where Used | Why Used | Interview Explanation |
|---------|-----------|----------|-----------------------|
| **Classes & Objects** | `Book`, `Student`, `BorrowRecord`, `LibraryService`, `Main` | Blueprint for real-world entities | A class defines structure and behavior; an object is a concrete instance of that class |
| **Encapsulation** | All model classes | Protect data from unauthorized access | Private fields + public getters/setters; data hiding principle |
| **Constructors** | `Book(...)`, `Student(...)`, `BorrowRecord(...)` | Initialize object state at creation | Constructor runs when `new` is called; sets mandatory fields |
| **Getter & Setter** | All model classes | Controlled access to private fields | Allows read/write with optional validation logic |
| **`ArrayList<T>`** | `books`, `students`, `borrowHistory` lists | Dynamic-size ordered collection | Backed by a resizable array; O(1) add, O(n) search |
| **`HashMap<String, Book>`** | `bookIndex` — maps Book ID → Book object | O(1) book lookup by ID for borrow/return/delete operations | Key-value store; eliminates linear scan for ID-based lookups |
| **`LocalDate`** | Borrow date, due date, return date in `BorrowRecord` | Immutable, thread-safe date representation | Part of `java.time` API introduced in Java 8 |
| **`ChronoUnit.DAYS`** | Fine calculation in `returnBook()` | Calculate exact day difference between two dates | `ChronoUnit.DAYS.between(dueDate, returnDate)` returns long days |
| **`switch` statement** | Menu selection in `Main.java` | Multi-branch decision on integer input | More readable than chained if-else for menu routing |
| **`for` loop** | Searching, validating, displaying records | Iterate over collection elements | Enhanced for-each loop used for readability |
| **`Scanner`** | User input in `Main.java` | Read text from standard input (console) | `nextLine()` reads full lines including spaces |
| **`try-catch`** | Numeric parsing, date parsing in `Main.java` | Handle invalid input without crashing | `NumberFormatException` when `Integer.parseInt()` receives non-numeric text |
| **`String.equalsIgnoreCase()`** | ID duplicate checks, lookups | Case-insensitive string comparison | Prevents `"B101"` and `"b101"` being treated as different IDs |
| **`String.contains()`** | Search book by keyword | Substring matching | Checks if the string contains the given character sequence |
| **`String.toLowerCase()`** | Search normalization | Uniform comparison without case bias | Converts both search query and target to lowercase before comparing |
| **`String.trim()`** | All user inputs | Remove leading/trailing whitespace | Prevents accidental mismatches due to extra spaces |
| **`HashMap<K,V>`** | Most Borrowed Book algorithm in `displayReports()` | O(1) frequency counting by book ID | Key-value store; key is bookId, value is borrow count |
| **`DateTimeParseException`** | Date input validation | Handle malformed date strings | Thrown by `LocalDate.parse()` when format does not match `YYYY-MM-DD` |

---

## 5. 🗄️ Data Structures Used

| Data Structure | Stores | Why Chosen | Insert | Search | Delete |
|---------------|--------|-----------|--------|--------|--------|
| `ArrayList<Book>` | All registered books | Maintains insertion order; simple iteration; dynamic sizing | O(1) amortized | O(n) linear scan | O(n) via `remove(object)` |
| `ArrayList<Student>` | All registered students | Same as above | O(1) amortized | O(n) | O(n) |
| `ArrayList<BorrowRecord>` | All borrow transactions | Historical log; sequential read for history display | O(1) amortized | O(n) | Not deleted |
| `HashMap<String, Book>` | Book ID → Book object (`bookIndex`) | O(1) book lookup for borrow, return, delete operations | O(1) | O(1) ✅ | O(1) |
| `HashMap<String, Integer>` | Book ID → borrow frequency in reports | O(1) frequency counting per book ID | O(1) | O(1) | O(1) |

> **Note:** `ArrayList` maintains the ordered display of books and students. `HashMap<String, Book>` (`bookIndex`) runs in parallel to give O(1) lookup by Book ID — both structures are always kept in sync on every `addBook()` and `deleteBook()` call.

---

## 6. 🔒 OOP Concepts Used

### Encapsulation
Encapsulation is the practice of hiding internal data and providing controlled access through methods.

**`Book` class:**
```java
private String bookId;
private int availableCopies;

public String getBookId() { return bookId; }
public void setAvailableCopies(int availableCopies) {
    this.availableCopies = availableCopies;
}
```
Direct modification of `availableCopies` from outside the class is prevented. The service layer calls `setAvailableCopies()`, ensuring centralized control.

**`Student` class:**
All personal information (`phone`, `email`, `studentId`) is private. Exposure happens only through public getters, following the principle of **minimum required access**.

**`BorrowRecord` class:**
The `dueDate` field has no public setter. It is derived automatically inside the constructor using `borrowDate.plusDays(14)`, ensuring the business rule (14-day period) is never bypassed.

### Why Private Variables Are Important
- Prevents external code from directly corrupting internal state.
- Allows the class to enforce business rules inside setters (e.g. validate before setting).
- Makes debugging easier — only the class itself can modify its own state.

### Inheritance and Polymorphism
> ⚠️ **Intentional Design Decision:** This project does not use inheritance or polymorphism. Since the goal is to clearly demonstrate Core Java fundamentals (Collections, Encapsulation, Sorting, Date API), introducing inheritance would add unnecessary complexity without educational value for this scope. In a production system, an abstract `LibraryEntity` base class could be introduced with `Book` and `Student` as subclasses.

---

## 7. 🔧 Detailed Feature Explanation

### 7.1 Add Book

**Objective:** Insert a new book into the in-memory books list, preventing duplicates.

**Internal Working:**
1. `Main.java` reads 8 fields via `scanner.nextLine()`.
2. `totalCopies` is parsed via `Integer.parseInt()` inside a `try-catch`.
3. A `Book` object is constructed. The constructor initializes `availableCopies = totalCopies`.
4. `LibraryService.addBook()` uses `bookIndex.containsKey(key)` for O(1) duplicate check.
5. If duplicate found → prints error and returns `false`. Else → adds to `books` list, puts into `bookIndex`, and returns `true`.

**Data Structures:** `ArrayList<Book>` + `HashMap<String, Book>` (bookIndex)
**Validation:** Duplicate Book ID (case-insensitive via `.toLowerCase()`), `totalCopies > 0`
**Time Complexity:** O(1) — HashMap duplicate check
**Interview Questions:**
- Why `equalsIgnoreCase()` instead of `equals()`?
- What happens if `Integer.parseInt()` receives `"abc"`?
- Why is `availableCopies` set in the constructor and not as a separate input?
- Why use a HashMap alongside the ArrayList?

---

### 7.2 View Books

**Objective:** Display all registered books in an aligned, readable console table.

**Internal Working:**
1. Checks if `books` list is empty → prints friendly message if so.
2. Iterates through `ArrayList<Book>` using enhanced `for` loop.
3. Uses `System.out.printf()` with format specifiers (`%-20s`, `%-5d`) for aligned columns.
4. A private `truncate()` helper trims long strings to fit column widths cleanly.

**Time Complexity:** O(n) — iterates all books once
**Interview Question:** What does `%-20s` mean in `printf()`?

---

### 7.3 Search Book

**Objective:** Find books matching a keyword in Book ID, Title, or Author fields.

**Internal Working:**
1. Converts the search keyword to lowercase using `toLowerCase()`.
2. Iterates through `books` list.
3. For each book, checks `getBookId()`, `getTitle()`, `getAuthor()` using `.toLowerCase().contains(query)`.
4. Matches are collected into a separate `ArrayList<Book> matchingBooks`.
5. If empty → prints "No results". Else → displays the table of matches.

**Time Complexity:** O(n) — single pass through the list
**Interview Question:** Why use `contains()` instead of `equals()` for search?

---

### 7.4 Delete Book

**Objective:** Remove a book from the library by Book ID.

**Internal Working:**
1. Converts the Book ID to lowercase → looks it up in `bookIndex` in O(1).
2. If not found in `bookIndex` → returns `false` immediately.
3. Calls `books.remove(book)` using the object reference (no index shift issues).
4. Calls `bookIndex.remove(key)` to keep both structures in sync.
5. Returns `true` on success.

**Time Complexity:** O(1) for lookup via HashMap; O(n) for `ArrayList.remove(object)` internally
**Interview Question:** Why must you remove from both the ArrayList and the HashMap when deleting a book?

---

### 7.5 Register Student

**Objective:** Add a new student to the system with validated personal details.

**Internal Working:**
1. Reads all student fields via `scanner.nextLine()`.
2. Validates phone: exactly 10 characters, all digits (using `Character.isDigit()` in a loop).
3. Validates email: must contain `@` using `String.contains()`.
4. `LibraryService.registerStudent()` checks for duplicate `studentId`.
5. Adds to `ArrayList<Student>` on success.

**Time Complexity:** O(n) for duplicate check
**Interview Question:** How do you validate a phone number without regex in Java?

---

### 7.6 View Students

**Objective:** Display all registered students in a formatted table.
**Working:** Same pattern as View Books — iterates `ArrayList<Student>`, prints via `printf()`.
**Time Complexity:** O(n)

---

### 7.7 Borrow Book

**Objective:** Issue a book to a student, managing availability and creating a transaction record.

**Internal Working:**
1. Looks up student by ID in `students` list (O(n)) → null check → error if missing.
2. Looks up book by ID via `bookIndex.get(key)` → **O(1)** → error if missing.
3. Checks `book.getAvailableCopies() <= 0` → error if no copies available.
4. Checks `borrowHistory` for an existing active record (same student + same book + `returnDate == null`) → error if already borrowed.
5. Decrements `availableCopies` by 1.
6. Creates `new BorrowRecord(student, book, borrowDate)` — constructor auto-sets `dueDate = borrowDate.plusDays(14)`.
7. Adds to `borrowHistory`.

**Time Complexity:** O(1) for book lookup (HashMap), O(n) for student lookup and active-borrow check
**Interview Question:** How is the due date calculated? What Java class is used?

---

### 7.8 Return Book

**Objective:** Process a book return, restore availability, and calculate overdue fine.

**Internal Working:**
1. Looks up student (O(n)) and book via `bookIndex` **(O(1))** → errors if not found.
2. Scans `borrowHistory` for an active record (`returnDate == null`) matching the student and book.
3. Validates `returnDate` is not before `borrowDate`.
4. Calculates fine:
   ```java
   long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
   double fine = daysLate * 10.0; // Rs. 10 per day
   ```
5. Sets `returnDate` and `fine` on the active record.
6. Increments `book.availableCopies` by 1.

**Time Complexity:** O(1) for book lookup (HashMap), O(n) for history scan
**Interview Question:** What class is used to calculate the number of days between two dates?

---

### 7.9 View Borrow History

**Objective:** Display all past and active borrow transactions in a formatted log table.
**Working:** Iterates full `borrowHistory` list. Displays "Not Returned" if `returnDate == null`.
**Time Complexity:** O(n)

---

### 7.10 Reports

**Objective:** Display statistical summary of the entire library state.

**Metrics Calculated:**
- Total unique book titles: `books.size()`
- Total copies: sum of `book.getTotalCopies()` across all books
- Available copies: sum of `book.getAvailableCopies()` across all books
- Active borrowed copies: count of `borrowHistory` records where `returnDate == null`
- Overdue books: count of active records where `LocalDate.now().isAfter(record.getDueDate())`
- Total students: `students.size()`
- Total historical checkouts: `borrowHistory.size()`
- Most Borrowed Book: uses `HashMap<String, Integer>` to count occurrences per book ID, then finds max

**Time Complexity:** O(n) for all calculations using a single pass through each list

---

## 8. ✅ Input Validations

| Validation | Reason | Java Method / Technique |
|------------|--------|------------------------|
| Duplicate Book ID | Prevent inconsistent data | `equalsIgnoreCase()` in a `for` loop |
| Duplicate Student ID | Prevent duplicate registrations | `equalsIgnoreCase()` in a `for` loop |
| Phone number = exactly 10 digits | Enforce format correctness | `length()` check + `Character.isDigit()` loop |
| Email contains `@` | Basic email format enforcement | `String.contains("@")` |
| Total copies > 0 | Books must have at least one copy | Parsed via `Integer.parseInt()` + conditional check |
| Book availability on borrow | Cannot issue a book with 0 copies | `getAvailableCopies() <= 0` check |
| No double borrowing | A student cannot borrow the same book twice | Scan `borrowHistory` for active matching record |
| Return date not before borrow date | Logical date integrity | `returnDate.isBefore(borrowDate)` using `LocalDate` |
| Invalid menu choice | Prevent unintended operations | `default` case in `switch` prints error |
| Non-numeric input for integers | Prevent `NumberFormatException` crash | `try-catch (NumberFormatException e)` |
| Invalid date format | Prevent `DateTimeParseException` crash | `try-catch (DateTimeParseException e)` around `LocalDate.parse()` |

---

## 9. ⚡ HashMap Book Index

### Why a Parallel HashMap?

The `books` ArrayList is great for ordered display and iteration, but searching by Book ID required an O(n) loop. By maintaining a parallel `HashMap<String, Book> bookIndex`, book lookups by ID become O(1).

```java
// Field declaration
private final Map<String, Book> bookIndex = new HashMap<>();

// On addBook() — register in both
books.add(book);
bookIndex.put(book.getBookId().trim().toLowerCase(), book);

// On deleteBook() — remove from both
books.remove(book);
bookIndex.remove(key);

// findBookById() — now O(1)
private Book findBookById(String bookId) {
    return bookIndex.get(bookId.trim().toLowerCase());
}
```

| Operation | Before (ArrayList only) | After (+ bookIndex HashMap) |
|-----------|------------------------|-----------------------------|
| Add book duplicate check | O(n) loop | O(1) `containsKey()` |
| Find book by ID (borrow/return) | O(n) loop | O(1) `get()` |
| Delete book | O(n) loop | O(1) lookup + O(n) `remove(object)` |

> **Key Rule:** Both `books` and `bookIndex` must always be updated together. Adding to one without the other would cause inconsistency.

---

## 10. 📅 Borrow and Return Logic

### Borrow Process
```
Student ID → validate exists
     ↓
Book ID → validate exists
     ↓
availableCopies > 0? → validate
     ↓
Active record exists? → validate (no double borrow)
     ↓
availableCopies-- (decrement)
     ↓
new BorrowRecord(student, book, borrowDate)
     ↓
dueDate = borrowDate.plusDays(14) [inside constructor]
     ↓
Add to borrowHistory list
```

### Return Process and Fine Calculation
```
Find active BorrowRecord (returnDate == null)
     ↓
returnDate.isBefore(borrowDate)? → validate
     ↓
daysLate = ChronoUnit.DAYS.between(dueDate, returnDate)
     ↓
if daysLate > 0:
    fine = daysLate × ₹10.00
     ↓
Set returnDate and fine on record
     ↓
availableCopies++ (restore)
```

### Key Java Time API Usage

| Operation | Java Code |
|-----------|-----------|
| Record today's date | `LocalDate.now()` |
| Set borrow date from user input | `LocalDate.parse("2026-07-01")` |
| Calculate due date (14 days) | `borrowDate.plusDays(14)` |
| Check if overdue | `today.isAfter(record.getDueDate())` |
| Calculate days late | `ChronoUnit.DAYS.between(dueDate, returnDate)` |

---

## 11. 📊 Report Generation

### Algorithm Overview
All report metrics are computed in a single method `displayReports()` in `LibraryService`, using one sequential pass through each list:

```
Pass 1: books list → sum totalCopies, sum availableCopies
Pass 2: borrowHistory list → count active, count overdue, count frequencies (via HashMap)
Pass 3: students.size() → instant O(1)
```

### Most Borrowed Book Algorithm
```java
Map<String, Integer> borrowCounts = new HashMap<>();
for (BorrowRecord record : borrowHistory) {
    String id = record.getBook().getBookId();
    int count = borrowCounts.getOrDefault(id, 0) + 1;
    borrowCounts.put(id, count);
    if (count > maxBorrows) {
        maxBorrows = count;
        mostBorrowedBook = record.getBook();
    }
}
```
**Complexity:** O(n) — single pass through borrow history.
Using `getOrDefault()` avoids a null check on the first occurrence of each book ID.

---

## 12. 📁 Project Directory

```
Library Management System/
│
├── src/
│   ├── model/
│   │   ├── Book.java           # Stores book details. Fields: bookId, title, author,
│   │   │                       # category, publisher, publicationYear, isbn,
│   │   │                       # totalCopies, availableCopies
│   │   │
│   │   ├── Student.java        # Stores student details. Fields: studentId, name,
│   │   │                       # branch, year, phone, email
│   │   │
│   │   └── BorrowRecord.java   # Stores transaction. Fields: student, book,
│   │                           # borrowDate, dueDate, returnDate, fine
│   │
│   ├── service/
│   │   └── LibraryService.java # Business logic: add/view/search/delete books,
│   │                           # register/view students, borrow/return/history,
│   │                           # sorting, reporting. Uses ArrayList<T> and HashMap.
│   │
│   └── main/
│       └── Main.java           # Entry point. Scanner loop, menu switch, input reads,
│                               # inline validation, handler methods per feature.
│
├── bin/                        # Auto-generated compiled .class files
├── README.md                   # This file
└── .gitignore                  # Excludes: bin/, *.dat, IDE configs
```

---

## 13. ⚙️ How to Compile

Navigate to the project root directory in your terminal:

```bash
javac -d bin src/model/*.java src/service/*.java src/main/*.java
```

**Flag explanation:**
- `javac` — The Java compiler
- `-d bin` — Output compiled `.class` files into the `bin/` directory
- `src/model/*.java` — Compile all Java files in the `model` package
- `src/service/*.java` — Compile all Java files in the `service` package
- `src/main/*.java` — Compile all Java files in the `main` package

> **Note:** Always compile in dependency order — models first, then service (which depends on models), then main (which depends on service).

---

## 14. ▶️ How to Run

After successful compilation:

```bash
java -cp bin main.Main
```

**Flag explanation:**
- `java` — The Java Virtual Machine launcher
- `-cp bin` — Set classpath to the `bin/` directory where `.class` files are located
- `main.Main` — Fully qualified class name (`package.ClassName`)

---

## 15. 🖼️ Sample Screenshots

> Add your screenshots to an `images/` folder in the project root and update the paths below.

**Main Menu**
```
images/menu.png
```

**Add Book**
```
images/add-book.png
```

**View Books Table**
```
images/view-books.png
```

**Borrow Book**
```
images/borrow-book.png
```

**Return Book with Fine**
```
images/return-book.png
```

**Library Reports**
```
images/reports.png
```

---

## 16. 🚀 Future Improvements

| Improvement | Status | Description | Technology |
|------------|--------|-------------|-----------|
| **HashMap book indexing** | ✅ Implemented | O(1) book lookup by ID using `bookIndex` HashMap | `HashMap<String, Book>` |
| **File persistence** | ⏳ Planned | Save data across sessions using Java Serialization or CSV files | `ObjectOutputStream`, `FileWriter` |
| **HashMap student indexing** | ⏳ Planned | Same O(1) pattern for student lookup by ID | `HashMap<String, Student>` |
| **Database integration** | ⏳ Planned | Move to JDBC with MySQL for persistent, scalable storage | JDBC, MySQL |
| **GUI** | ⏳ Planned | Add a desktop interface for non-technical users | JavaFX / Swing |
| **Spring Boot REST API** | ⏳ Planned | Convert to a REST backend with JSON responses | Spring Boot, REST |
| **Authentication** | ⏳ Planned | Add librarian login / admin module | Password hashing, sessions |
| **Book reservation** | ⏳ Planned | Let students reserve books currently on loan | `Queue<Student>` |
| **Regex validation** | ⏳ Planned | Replace manual phone/email validation with regex patterns | `Pattern`, `Matcher` |
| **Unit Testing** | ⏳ Planned | Add automated tests for service layer | JUnit 5 |

---

## 17. ❓ Interview Questions Based on This Project

### Java Fundamentals
1. What is the difference between `==` and `.equals()` in Java?
2. Why do we use `equalsIgnoreCase()` instead of `equals()` for ID comparison?
3. What is `NullPointerException` and where could it occur in this project?
4. What does `trim()` do and why is it used on every input?
5. What is `Integer.parseInt()` and what exception does it throw on invalid input?

### Collections Framework
6. Why did you use `ArrayList` instead of an array?
7. What is the difference between `ArrayList` and `LinkedList`?
8. What is the time complexity of `ArrayList.add()`, `get()`, and `remove()`?
9. How does `HashMap` work internally?
10. Why did you use `HashMap` for the Most Borrowed Book algorithm?

### OOP Concepts
11. Explain encapsulation with an example from this project.
12. Why are fields in `Book` marked `private`?
13. What is the difference between a constructor and a method?
14. Why does `BorrowRecord` not have a public setter for `dueDate`?
15. Why was inheritance not used in this project?

### Design Decisions
16. Why did you separate the project into `model`, `service`, and `main` packages?
17. What would you change if you had to support 1 million books?
18. How would you add authentication to this application?

### HashMap & Performance
19. Why did you use a HashMap alongside the ArrayList for books?
20. What happens to the HashMap when you delete a book?
21. Why store `bookId.toLowerCase()` as the key in the HashMap?
22. What is the time complexity of `HashMap.get()` and `HashMap.put()`?

### Java Time API
23. What is `LocalDate` and why is it preferred over `java.util.Date`?
24. How do you calculate the number of days between two `LocalDate` values?
25. What is `ChronoUnit` and how is it used in the fine calculation?

---

## 18. 🎓 Learning Outcomes

By building and understanding this project, a student develops the following engineering skills:

| Skill | What You Learn |
|-------|---------------|
| **OOP Design** | How to model real-world entities as classes with proper encapsulation |
| **Collections Mastery** | Practical use of `ArrayList` and `HashMap`, and keeping dual structures in sync |
| **Input Handling** | Reading console input safely with `Scanner`, handling format exceptions gracefully |
| **Business Logic Separation** | Keeping UI code separate from business rules — the foundation of clean architecture |
| **Performance Awareness** | Using a HashMap index to reduce O(n) lookups to O(1) without replacing the ArrayList |
| **Date Handling** | Using the modern `java.time` API for date arithmetic and duration calculation |
| **Defensive Programming** | Validating all inputs before processing to prevent crashes and data corruption |
| **Code Readability** | Writing self-documenting method names, using helper methods, and adding meaningful comments |
| **Git & Version Control** | Managing source code history with meaningful commit messages |
| **Interview Confidence** | Ability to explain every line of code, every design decision, and every tradeoff |

---

> 📌 **Built with:** Core Java (JDK 8+) | No external libraries | No databases | No frameworks
