package bookshelf;

import bookshelf.books.Book;
import bookshelf.books.BookShelf;
import bookshelf.users.User;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class HomePageBookshelf {

    static User user = login();

    public static void main(String[] args) throws IOException {


        boolean flag = false;
        BookShelf bookShelf = new BookShelf();

        while (!flag) {
            if (user.getRole() == null) {
                user = login();
            } else if (user.getRole()) {
                adminPage(bookShelf);
            } else if (!user.getRole()) {
                userPage(bookShelf);
            }
        }
    }

    public static void userPage(BookShelf bookShelf) {
        System.out.println("enter number of actions what do you do :");
        System.out.println("1.See all books\\ 2.Search  book \\ 3.Suggest adding a book \\ 4.LogOut");
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();
        if (action.equals("1")) {
            showBooks(bookShelf);
        } else if (action.equals("2")) {
            System.out.println("Enter the parameter by which you will search for the book");
            System.out.println("1. Name book \\ 2. Name author \\ 3. Year of the book");
            String parameter = scanner.nextLine();
            if (parameter.equals("1")) {
                String name = scanner.nextLine();
                List<Book> foundBooks = bookShelf.searchBookByName(name);
                for (Book book : foundBooks) {
                    System.out.println(book.toString());
                }
            } else if (parameter.equals("2")) {
                String name = scanner.nextLine();
                List<Book> foundBooks = bookShelf.searchBookByAuthor(name);
                for (Book book : foundBooks) {
                    System.out.println(book.toString());
                }
            } else if (parameter.equals("3")) {
                int year = scanner.nextInt();
                List<Book> foundBooks = bookShelf.searchBookByYear(year);
                for (Book book : foundBooks) {
                    System.out.println(book.toString());
                }
            } else {
                System.out.println("wrong parameter");
            }
        } else if (action.equals("3")) {
            bookShelf.addBookFromUser(enterAllParameter());
        } else if (action.equals("4")) {
            user.setRole(null);
        } else {
            System.out.println("wrong number");
        }
    }

    public static void adminPage(BookShelf bookShelf) {
        System.out.println("enter number of actions what do you do :");
        System.out.println("1.See all books \\ 2.Delete book \\ 3.Add book \\ 4.LogOut");
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();
        if (action.equals("1")) {
            showBooks(bookShelf);
        } else if (action.equals("2")) {
            if (bookShelf.deleteBook(enterAllParameter())) {
                System.out.println("Book was delete");
            } else {
                System.out.println("Book with this options not found");
            }
        } else if (action.equals("3")) {
            bookShelf.addBook(enterAllParameter());
            System.out.println("Book was add");
        } else if (action.equals("4")) {
            user.setRole(null);
        } else {
            System.out.println("wrong number");
        }
    }

    public static void showBooks(BookShelf bookShelf) {
        int i = 0;
        for (Book book : bookShelf.getAllBooks()) {
            i++;
            System.out.println(book.toString());
            if (i == 5) {
                System.out.println("press enter to continue");
                Scanner key = new Scanner(System.in);
                key.nextLine();
            }
        }
    }

    public static Book enterAllParameter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name book you want to add");
        String name = scanner.nextLine();
        System.out.println("Enter author of the book you want to add");
        String author = scanner.nextLine();
        System.out.println("Enter year of the book you want to add");
        int year = 0;
        boolean result = false;
        while (!result) {
            try {
                year = scanner.nextInt();
                result = true;
            } catch (Exception e) {
                System.out.println("Enter number!");
                result = false;
            }
        }
        System.out.println("Enter how many pages book have");
        int pages = 0;
        result = false;
        while (!result) {
            try {
                pages = scanner.nextInt();
                result = true;
            } catch (Exception e) {
                System.out.println("Enter number!");
                result = false;
            }
        }
        return new Book(name, author, year, pages);
    }

    public static User login() {
        boolean flag = false;
        User user = null;
        while (!flag) {
            System.out.println("Enter your login admin/user");
            Scanner scanner = new Scanner(System.in);
            String login = scanner.nextLine();
            System.out.println("Enter your password");
            String password = scanner.nextLine();
            user = new User(login, password);
            if (user.getLogin() != null) {
                flag = true;
            } else {
                System.out.println("wrong login or password");
            }
        }
        return user;
    }
}
