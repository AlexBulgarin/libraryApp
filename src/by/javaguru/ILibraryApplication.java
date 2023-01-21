package by.javaguru;

public interface ILibraryApplication {
    String add(String login, String pwd);
    String getBook(String login, String pwd, String bookName);
    boolean pushData();
    boolean pullData();
    void print();
    void addBook(String name, String author, int count);
}
