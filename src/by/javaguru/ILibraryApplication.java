package by.javaguru;

public interface ILibraryApplication {
    void add(String login, String pwd);

    void delete(String login, String pwd);

    void update(String login, String oldPwd, String newPwd);

    void getBook(String login, String pwd, String bookName);

    void returnBook(String login, String pwd, String bookName);

    boolean pushData();

    boolean pullData();

    void print();

    void addBook(String name, String author, int count);
}
