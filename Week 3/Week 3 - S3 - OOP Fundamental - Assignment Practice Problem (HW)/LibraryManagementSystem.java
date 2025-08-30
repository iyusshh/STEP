import java.util.*;

class Book {
    private String bookId, title, author, isbn, category, issueDate, dueDate;
    private boolean isIssued;
    static int totalBooks = 0;
    static String libraryName = "City Library";
    static double finePerDay = 10.0;
    static int maxBooksAllowed = 3;

    Book(String bookId, String title, String author, String isbn, String category) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.isIssued = false;
        this.issueDate = "";
        this.dueDate = "";
        totalBooks++;
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public boolean isIssued() { return isIssued; }
    public void setIssued(boolean issued) { this.isIssued = issued; }
    public void setIssueDate(String date) { this.issueDate = date; }
    public void setDueDate(String date) { this.dueDate = date; }
    public String getDueDate() { return dueDate; }
}

class Member {
    private String memberId, memberName, memberType, membershipDate;
    private Book[] booksIssued;
    private int issuedCount = 0;
    private double totalFines;
    static int totalMembers = 0;

    Member(String memberId, String memberName, String memberType, String membershipDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberType = memberType;
        this.membershipDate = membershipDate;
        this.booksIssued = new Book[Book.maxBooksAllowed];
        this.totalFines = 0.0;
        totalMembers++;
    }

    public String getMemberId() { return memberId; }
    public String getMemberName() { return memberName; }
    public Book[] getBooksIssued() { return booksIssued; }
    public int getIssuedCount() { return issuedCount; }
    public double getTotalFines() { return totalFines; }

    public boolean issueBook(Book book, String issueDate, String dueDate) {
        if (issuedCount >= Book.maxBooksAllowed || book.isIssued()) return false;
        booksIssued[issuedCount++] = book;
        book.setIssued(true);
        book.setIssueDate(issueDate);
        book.setDueDate(dueDate);
        return true;
    }

    public boolean returnBook(String bookId, String returnDate) {
        for (int i = 0; i < issuedCount; i++) {
            if (booksIssued[i] != null && booksIssued[i].getBookId().equals(bookId)) {
                double fine = calculateFine(booksIssued[i].getDueDate(), returnDate);
                totalFines += fine;
                booksIssued[i].setIssued(false);
                booksIssued[i] = booksIssued[issuedCount - 1];
                booksIssued[issuedCount - 1] = null;
                issuedCount--;
                return true;
            }
        }
        return false;
    }

    public boolean renewBook(String bookId, String newDueDate) {
        for (int i = 0; i < issuedCount; i++) {
            if (booksIssued[i] != null && booksIssued[i].getBookId().equals(bookId)) {
                booksIssued[i].setDueDate(newDueDate);
                return true;
            }
        }
        return false;
    }

    private double calculateFine(String dueDate, String returnDate) {
        try {
            String[] d1 = dueDate.split("-");
            String[] d2 = returnDate.split("-");
            Calendar due = Calendar.getInstance();
            Calendar ret = Calendar.getInstance();
            due.set(Integer.parseInt(d1[2]), Integer.parseInt(d1[1]), Integer.parseInt(d1[0]));
            ret.set(Integer.parseInt(d2[2]), Integer.parseInt(d2[1]), Integer.parseInt(d2[0]));
            long diff = ret.getTimeInMillis() - due.getTimeInMillis();
            long days = diff / (1000 * 60 * 60 * 24);
            return days > 0 ? days * Book.finePerDay : 0.0;
        } catch (Exception e) {
            return 0.0;
        }
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();

    public void addBook(Book book) { books.add(book); }
    public void addMember(Member member) { members.add(member); }

    public Book searchBookById(String bookId) {
        for (Book book : books) if (book.getBookId().equals(bookId)) return book;
        return null;
    }

    public void generateLibraryReport() {
        System.out.println("Library: " + Book.libraryName);
        System.out.println("Total Books: " + Book.totalBooks);
        System.out.println("Total Members: " + Member.totalMembers);
    }

    public void getOverdueBooks(String currentDate) {
        System.out.println("Overdue Books as of " + currentDate + ":");
        for (Member m : members) {
            for (Book b : m.getBooksIssued()) {
                if (b != null && b.isIssued()) {
                    double fine = calculateFine(b.getDueDate(), currentDate);
                    if (fine > 0) System.out.println(b.getTitle() + " - Fine: Rs." + fine);
                }
            }
        }
    }

    private double calculateFine(String dueDate, String returnDate) {
        try {
            String[] d1 = dueDate.split("-");
            String[] d2 = returnDate.split("-");
            Calendar due = Calendar.getInstance();
            Calendar ret = Calendar.getInstance();
            due.set(Integer.parseInt(d1[2]), Integer.parseInt(d1[1]), Integer.parseInt(d1[0]));
            ret.set(Integer.parseInt(d2[2]), Integer.parseInt(d2[1]), Integer.parseInt(d2[0]));
            long diff = ret.getTimeInMillis() - due.getTimeInMillis();
            long days = diff / (1000 * 60 * 60 * 24);
            return days > 0 ? days * Book.finePerDay : 0.0;
        } catch (Exception e) {
            return 0.0;
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Book b1 = new Book("B001", "Java Basics", "James Gosling", "12345", "Programming");
        Book b2 = new Book("B002", "Python Crash", "Guido Van Rossum", "54321", "Programming");
        Book b3 = new Book("B003", "DSA Handbook", "Narsimha", "67890", "Algorithms");
        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);
        Member m1 = new Member("M001", "Aayush Rai", "Student", "01-01-2025");
        Member m2 = new Member("M002", "Ravi Kumar", "Faculty", "15-02-2025");
        library.addMember(m1);
        library.addMember(m2);
        m1.issueBook(b1, "01-08-2025", "15-08-2025");
        m1.issueBook(b2, "01-08-2025", "15-08-2025");
        m2.issueBook(b3, "02-08-2025", "12-08-2025");
        m1.returnBook("B001", "25-08-2025");
        library.generateLibraryReport();
        library.getOverdueBooks("30-08-2025");
    }
}
