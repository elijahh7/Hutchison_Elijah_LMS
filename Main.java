import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Name: Elijah Hutchison
 * Course: CEN-3024C-24667
 * Date: 1/16/2024
 *
 * Class: Book
 * Description: Represents a book with an ID, title, and author.
 */
class Book {
    int id;
    String title;
    String author;

    /**
     * Constructor for the Book class.
     *
     * @param id     The unique ID of the book.
     * @param title  The title of the book.
     * @param author The author of the book.
     */
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    /**
     * Override toString method to provide a string representation of the Book.
     *
     * @return A string representation of the Book.
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author;
    }
}

/**
 * Name: Elijah Hutchison
 * Course: CEN-3024C-24667
 * Date: 1/16/2024
 *
 * Class: LibraryManagementSystem
 * Description: Manages the collection of books in the library.
 */
class LibraryManagementSystem {
    private ArrayList<Book> collection = new ArrayList<>();

    /**
     * Adds books to the collection from a text file.
     *
     * @param filePath The path to the text file containing book information.
     */
    public void addBooksFromFile(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String title = parts[1].trim();
                String author = parts[2].trim();
                collection.add(new Book(id, title, author));
            }
            scanner.close();
            System.out.println("Books added successfully.");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        }
    }

    /**
     * Removes a book from the collection by its ID.
     *
     * @param bookId The ID of the book to be removed.
     */
    public void removeBookById(int bookId) {
        Iterator<Book> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.id == bookId) {
                iterator.remove();
                System.out.println("Book removed successfully.");
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    /**
     * Lists all books currently in the collection.
     */
    public void listAllBooks() {
        if (collection.isEmpty()) {
            System.out.println("No books in the collection.");
        } else {
            System.out.println("Books in the collection:");
            for (Book book : collection) {
                System.out.println(book);
            }
        }
    }

    /**
     * Adds a new book to the collection if the ID is not already in use.
     *
     * @param id     The unique ID of the new book.
     * @param title  The title of the new book.
     * @param author The author of the new book.
     */
    public void addNewBook(int id, String title, String author) {
        // Check if the ID is already in use
        for (Book book : collection) {
            if (book.id == id) {
                System.out.println("Cannot add the book. ID " + id + " is already in use.");
                return;
            }
        }

        // If the ID is not in use, add the new book
        collection.add(new Book(id, title, author));
        System.out.println("New book added successfully.");
    }

}

/**
 * Name: Elijah Hutchison
 * Course: CEN-3024C-24667
 * Date: 1/16/2024
 *
 * Class: Main
 * Description: Contains the main method to demonstrate the Library Management System functionality.
 */

public class Main {
    public static void main(String[] args) {
        LibraryManagementSystem lms = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        // Add books from a text file
        lms.addBooksFromFile("C:\\Users\\purpl\\OneDrive\\Documents\\LMSTextFile.txt");

        while (true) {
            // Display menu
            System.out.println("\nMenu:");
            System.out.println("1. Add a new book");
            System.out.println("2. Remove a book by ID");
            System.out.println("3. Display all books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add a new book
                    System.out.println("\nEnter details to add a new book:");
                    System.out.print("Enter ID: ");
                    int newBookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Title: ");
                    String newBookTitle = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String newBookAuthor = scanner.nextLine();
                    lms.addNewBook(newBookId, newBookTitle, newBookAuthor);
                    break;

                case 2:
                    // Remove a book by ID
                    System.out.print("\nEnter ID to remove a book: ");
                    int bookIdToRemove = scanner.nextInt();
                    lms.removeBookById(bookIdToRemove);
                    break;

                case 3:
                    // Display all books
                    lms.listAllBooks();
                    break;

                case 4:
                    // Exit the program
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    break;
            }
        }
    }
}

