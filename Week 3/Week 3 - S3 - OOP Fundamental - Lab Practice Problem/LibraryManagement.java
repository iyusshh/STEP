class Book {
    private String bookId, title, author;
    private boolean isAvailable;
    private static int totalBooks = 0, availableBooks = 0;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        totalBooks++;
        availableBooks++;
    }

    public String getBookId() {
        return bookId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void issueBook() {
        if (isAvailable) {
            isAvailable = false;
            availableBooks--;
            System.out.println(title + " issued.");
        } else System.out.println(title + " is not available.");
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            availableBooks++;
            System.out.println(title + " returned.");
        }
    }

    public void displayBookInfo() {
        System.out.println(bookId + " | " + title + " | " + author + " | " + (isAvailable ? "Available" : "Issued"));
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    public static int getAvailableBooks() {
        return availableBooks;
    }
}

class Member {
    private String memberId, memberName;
    private String[] booksIssued;
    private int bookCount;

    public Member(String memberId, String memberName, int maxBooks) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.booksIssued = new String[maxBooks];
        this.bookCount = 0;
    }

    public void borrowBook(Book book) {
        if (book.isAvailable() && bookCount < booksIssued.length) {
            book.issueBook();
            booksIssued[bookCount++] = book.getBookId();
        } else {
            System.out.println(memberName + " cannot borrow " + book.getBookId());
        }
    }

    public void returnBook(String bookId, Book[] books) {
        for (int i = 0; i < bookCount; i++) {
            if (booksIssued[i].equals(bookId)) {
                for (Book b : books) {
                    if (b.getBookId().equals(bookId)) {
                        b.returnBook();
                        booksIssued[i] = booksIssued[--bookCount];
                        booksIssued[bookCount] = null;
                        return;
                    }
                }
            }
        }
        System.out.println(memberName + " hasn't issued " + bookId);
    }

    public void displayMemberInfo() {
        System.out.print(memberId + " | " + memberName + " | Books: ");
        if (bookCount == 0) System.out.println("None");
        else {
            for (int i = 0; i < bookCount; i++) System.out.print(booksIssued[i] + " ");
            System.out.println();
        }
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
        Book[] books = {
            new Book("B001", "Java Basics", "James"),
            new Book("B002", "DSA", "Karumanchi"),
            new Book("B003", "OS", "Galvin")
        };

        Member[] members = {
            new Member("M001", "Aayush Rai", 2),
            new Member("M002", "Rohan Sharma", 2)
        };

        members[0].borrowBook(books[0]);
        members[0].borrowBook(books[1]);
        members[1].borrowBook(books[1]);
        members[1].borrowBook(books[2]);

        members[0].returnBook("B001", books);
        members[1].returnBook("B002", books);

        System.out.println("\n--- Books ---");
        for (Book b : books) b.displayBookInfo();

        System.out.println("\n--- Members ---");
        for (Member m : members) m.displayMemberInfo();

        System.out.println("\nTotal Books: " + Book.getTotalBooks());
        System.out.println("Available Books: " + Book.getAvailableBooks());
    }
}
