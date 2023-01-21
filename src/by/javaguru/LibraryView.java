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
                case "info":
                    System.out.println(rb.getString("lb.info"));
                    break;
                case "add":
                    System.out.println("Введите логин:");
                    String login = scanner.next();
                    System.out.println("Введите пароль:");
                    String pwd = scanner.next();
                    String result = libraryApp.add(login, pwd);
                    System.out.println(result);
                    break;
                case "delete":
                    break;
                case "update":
                    break;
                case "addbook":
                    System.out.println("Введите название книги:");
                    String name = scanner.next();
                    System.out.println("Введите автора:");
                    String author = scanner.next();
                    System.out.println("Введите количесвто книг:");
                    int count = scanner.nextInt();
                    libraryApp.addBook(name, author, count);
                    break;
                case "borrowbook":
                    break;
                case "returnbook":
                    break;
                case "chooselanguage":
                    System.out.println("Choose language: EN or RU | Выберите языка: EN или RU");
                    String language = scanner.next();
                    if (language.equals("EN")) {
                        loadProperties("en");
                    } else if (language.equals("RU")) {
                        loadProperties("ru");
                    } else {
                        loadProperties("ru");
                    }
                    break;
                case "print":
                    libraryApp.print();
                    break;
                default:
                    System.out.println("Такой команды нет, наберите 'info' " +
                            "чтобы получить список команд");

            }
        }
    }

    private static void init(Scanner scanner, ILibraryApplication libraryApp) {
        libraryApp.pullData();
        System.out.println("Choose language: EN or RU | Выберите языка: EN или RU");
        String language = scanner.next();
        if (language.equals("EN")) {
            loadProperties("en");
        } else if (language.equals("RU")) {
            loadProperties("ru");
        } else {
            loadProperties("ru");
        }
        System.out.println(rb.getString("lb.info"));
    }

    private static void loadProperties(String languege) {
        Locale locale = new Locale(languege);
        rb = ResourceBundle.getBundle("library", locale);
    }
}


























