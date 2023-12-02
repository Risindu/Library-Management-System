import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

// Main class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Sample books
        Book book1 = new Book("Madoldoowa", "Martin Wickramasinghe", "4565");
        Book book2 = new Book("Colpetty People", "Ashok Ferrey", "1238");
        Book book3 = new Book("The Language of the Geckos", "Ananda Wickramasinghe", "9195");
        Book book4 = new Book("A Change of Skies", "Yasmine Gooneratne", "8538");
        Book book5 = new Book("Reef", "Romesh Gunesekera", "2166");
        Book book6 = new Book("Chinaman", "Shehan Karunatilaka", "7524");
        Book book7 = new Book("Funny Boy", "Shyam Selvadurai", "6239");
        Book book8 = new Book("Island of a Thousand Mirrors", "Nayomi Munaweera", "3947");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);
        library.addBook(book6);
        library.addBook(book7);
        library.addBook(book8);

        // Sample user
        User user = new User(1);

        while (true) {
            System.out.println("\n1. Display Available Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Add a Book");
            System.out.println("5. Delete a Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    library.displayAvailableBooks();
                    break;
                case 2:
                    System.out.print("Enter the ISBN of the book to borrow: ");
                    String borrowIsbn = scanner.nextLine();
                    library.borrowBook(borrowIsbn);
                    user.borrowBook(borrowIsbn);
                    break;
                case 3:
                    System.out.print("Enter the ISBN of the book to return: ");
                    String returnIsbn = scanner.nextLine();
                    library.returnBook(returnIsbn);
                    user.returnBook(returnIsbn);
                    break;
                case 4:
                    addBook(library, scanner);
                    break;
                case 5:
                    System.out.print("Enter the ISBN of the book to delete: ");
                    String deleteIsbn = scanner.nextLine();
                    library.deleteBook(deleteIsbn);
                    break;
                case 6:
                    System.out.println("Exiting program. Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void addBook(Library library, Scanner scanner) {
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        System.out.print("Enter the author of the book: ");
        String author = scanner.nextLine();
        System.out.print("Enter the ISBN of the book: ");
        String isbn = scanner.nextLine();

        Book newBook = new Book(title, author, isbn);
        library.addBook(newBook);

        System.out.println("Book added successfully!");
    }
}

// Book class
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean available;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Available: " + available;
    }
}

// Library class
class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        book.setAvailable(true); // Set new books as available by default
        books.add(book);
    }

    public void displayAvailableBooks() {
        List<Book> availableBooks = getAvailableBooks();
        System.out.println("Available Books:");
        for (Book book : availableBooks) {
            System.out.println(book);
        }
    }

    private List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public void borrowBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && book.isAvailable()) {
                book.setAvailable(false);
                System.out.println("Book borrowed successfully!");
                return;
            }
        }
        System.out.println("Book not available for borrowing.");
    }

    public void returnBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && !book.isAvailable()) {
                book.setAvailable(true);
                System.out.println("Book returned successfully!");
                return;
            }
        }
        System.out.println("Invalid ISBN or book not borrowed.");
    }

    public void deleteBook(String isbn) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getIsbn().equals(isbn)) {
                iterator.remove();
                System.out.println("Book deleted successfully!");
                return;
            }
        }
        System.out.println("Book not found for deletion.");
    }
}

// User class
class User {
    private int userId;
    private List<String> borrowedBooks;

    public User(int userId) {
        this.userId = userId;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(String isbn) {
        borrowedBooks.add(isbn);
    }

    public void returnBook(String isbn) {
        borrowedBooks.remove(isbn);
    }
}
