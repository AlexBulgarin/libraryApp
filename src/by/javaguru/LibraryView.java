package by.javaguru;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LibraryView {

    public static ResourceBundle rb;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ILibraryApplication libraryApp = new LibraryController();
        init(scanner, libraryApp);

        while (true) {
            String action = scanner.next();
            switch (action) {
                case "info" -> System.out.println(rb.getString("lb.info"));
                case "add" -> {
                    System.out.println(rb.getString("enterLogin"));
                    String login = scanner.next();
                    System.out.println(rb.getString("enterPassword"));
                    String pwd = scanner.next();
                    libraryApp.add(login, pwd);
                }
                case "delete" -> {
                    System.out.println(rb.getString("enterLogin"));
                    String login1 = scanner.next();
                    System.out.println(rb.getString("enterPassword"));
                    String pwd1 = scanner.next();
                    libraryApp.delete(login1, pwd1);
                }
                case "update" -> {
                    System.out.println(rb.getString("enterLogin"));
                    String login2 = scanner.next();
                    System.out.println(rb.getString("enterOldPassword"));
                    String oldPwd = scanner.next();
                    System.out.println(rb.getString("enterNewPassword"));
                    String newPwd = scanner.next();
                    libraryApp.update(login2, oldPwd, newPwd);
                }
                case "addbook" -> {
                    System.out.println(rb.getString("enterBookName"));
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    System.out.println(rb.getString("enterAuthor"));
                    String author = scanner.next();
                    System.out.println(rb.getString("enterBookQuantity"));
                    int count = scanner.nextInt();
                    libraryApp.addBook(name, author, count);
                }
                case "borrowbook" -> {
                    System.out.println(rb.getString("enterLogin"));
                    String login3 = scanner.next();
                    System.out.println(rb.getString("enterPassword"));
                    String pwd3 = scanner.next();
                    System.out.println(rb.getString("enterBookName"));
                    scanner.nextLine();
                    String name3 = scanner.nextLine();
                    libraryApp.getBook(login3, pwd3, name3);
                }
                case "returnbook" -> {
                    System.out.println(rb.getString("enterLogin"));
                    String login4 = scanner.next();
                    System.out.println(rb.getString("enterPassword"));
                    String pwd4 = scanner.next();
                    System.out.println(rb.getString("enterBookName"));
                    scanner.nextLine();
                    String name4 = scanner.nextLine();
                    libraryApp.returnBook(login4, pwd4, name4);
                }
                case "changelanguage" -> {
                    System.out.println("Choose language: EN or RU | Выберите языка: EN или RU");
                    String language = scanner.next().toLowerCase();
                    if (language.equals("en")) {
                        loadProperties("en");
                    } else if (language.equals("ru")) {
                        loadProperties("ru");
                    } else {
                        loadProperties("ru");
                    }
                    System.out.println(rb.getString("lb.info"));
                }
                case "print" -> libraryApp.print();
                default -> System.out.println(rb.getString("wrongCommand"));
            }
        }
    }

    private static void init(Scanner scanner, ILibraryApplication libraryApp) {
        libraryApp.pullData();
        System.out.println("Choose language: EN or RU | Выберите языка: EN или RU");
        String language = scanner.next().toLowerCase();
        if (language.equals("en")) {
            loadProperties("en");
        } else if (language.equals("ru")) {
            loadProperties("ru");
        } else {
            loadProperties("ru");
        }
        System.out.println(rb.getString("lb.info"));
    }

    private static void loadProperties(String language) {
        Locale locale = new Locale(language);
        rb = ResourceBundle.getBundle("library", locale);
    }
}


























