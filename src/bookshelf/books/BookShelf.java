package bookshelf.books;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookShelf {
    private List<Book> allBooks;

    public BookShelf() throws IOException {
        this.allBooks = new ArrayList<>();
        creatBookshelf();
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }

    private void creatBookshelf() throws IOException {
        BufferedReader buffer = new BufferedReader(new FileReader("src/bookshelf/books/AllBooks.txt"));
        String str = buffer.readLine();
        while (str != null) {
            String[] bookStr = str.split(":");
            Book book = new Book(bookStr[0], bookStr[1], Integer.parseInt(bookStr[2]), Integer.parseInt(bookStr[3]));
            allBooks.add(book);
            str = buffer.readLine();
        }
    }

    public boolean deleteBook(Book book) {
        for (Book b : allBooks) {
            if (b.getName().equals(book.getName()) && (b.getAuthor().equals(book.getAuthor()))
                    && (b.getYear() == book.getYear()) && (b.getPages() == book.getPages())) {
                allBooks.remove(b);
                return true;
            }
        }
        return false;
    }

    public void addBook(Book book) {
        this.allBooks.add(book);
    }

    public List<Book> searchBookByName(String name) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getName().equals(name)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> searchBookByAuthor(String name) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getAuthor().equals(name)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> searchBookByYear(int year) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getYear() == year) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public void addBookFromUser(Book book){
        // send email to admin
    }
}
