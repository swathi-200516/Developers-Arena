import java.util.*;
class Book {
    private String isbn, title, author, genre;
    private boolean available = true;

    public Book(String isbn, String title, String author, String genre) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean a) { available = a; }

    public void display() {
        System.out.println(isbn + " | " + title + " | " + author +
                " | " + genre + " | " + (available ? "Available" : "Issued"));
    }
}
class Member {
    private int memberId;
    private String name, contact;
    private List<Book> borrowedBooks = new ArrayList<>();

    public Member(int id, String name, String contact) {
        this.memberId = id;
        this.name = name;
        this.contact = contact;
    }

    public int getMemberId() { return memberId; }
    public String getName() { return name; }

    public void borrowBook(Book b) { borrowedBooks.add(b); }
    public void returnBook(Book b) { borrowedBooks.remove(b); }
}

class Library {
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    public void addBook(Book b) { books.add(b); }
    public void addMember(Member m) { members.add(m); }

    private Book findBook(String isbn) {
        for (Book b : books)
            if (b.getIsbn().equals(isbn)) return b;
        return null;
    }

    private Member findMember(int id) {
        for (Member m : members)
            if (m.getMemberId() == id) return m;
        return null;
    }

    public void showBooks() {
        for (Book b : books) b.display();
    }

    public void searchBook(String key) {
        for (Book b : books)
            if (b.getTitle().toLowerCase().contains(key.toLowerCase()) ||
                b.getAuthor().toLowerCase().contains(key.toLowerCase()))
                b.display();
    }

    public void borrowBook(int mid, String isbn) {
        Book b = findBook(isbn);
        Member m = findMember(mid);

        if (b == null || m == null || !b.isAvailable()) {
            System.out.println("Book not available");
            return;
        }
        b.setAvailable(false);
        m.borrowBook(b);
        System.out.println("Book borrowed successfully");
    }

    public void returnBook(int mid, String isbn) {
        Book b = findBook(isbn);
        Member m = findMember(mid);

        if (b != null && m != null) {
            b.setAvailable(true);
            m.returnBook(b);
            System.out.println("Book returned successfully");
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        lib.addBook(new Book("B1", "Java", "Gosling", "Programming"));
        lib.addBook(new Book("B2", "Python", "Guido", "Programming"));
        lib.addMember(new Member(1, "swathi", "7654897380"));

        while (true) {
            System.out.println("\n1.View 2.Search 3.Borrow 4.Return 5.Exit");
            int ch = sc.nextInt(); sc.nextLine();

            if (ch == 1) lib.showBooks();
            else if (ch == 2) lib.searchBook(sc.nextLine());
            else if (ch == 3) lib.borrowBook(sc.nextInt(), sc.next());
            else if (ch == 4) lib.returnBook(sc.nextInt(), sc.next());
            else break;
        }
    }
}