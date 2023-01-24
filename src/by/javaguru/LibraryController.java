package by.javaguru;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class LibraryController implements ILibraryApplication {

    public static Library library = new Library();

    @Override
    public void add(String login, String pwd) {
        if (Pattern.compile("[^A-Za-z]").matcher(login).find()) {
            System.out.println("Введен некорректный логин. Логин должен состоять только из букв|" +
                    "Incorrect login. Login should contain only letters");
        } else if (library.getReaderMap().containsKey(login)) {
            System.out.println("Пользователь под таким логином уже зарегестрирован, попробуйте другой|" +
                    "Login is already used, try another one");
        } else if (Pattern.compile("[^A-Za-z0-9]").matcher(pwd).find()) {
            System.out.println("Введен некорректный пароль. Пароль должен состоять только из букв и цифр|" +
                    "Incorrect password. Password should contain only letters and digits");
        } else {
            library.getReaderMap().put(login, new Reader(login, pwd));
            System.out.println(pushData() ? "Читателю | Reader " + login + " выдан читательский билет | " +
                    "has received library card" : "По техническим причинам выдать читательский билет не удалось | " +
                    "Due to technical reasons, it was not possible to issue a library card " + login);
        }
    }

    @Override
    public void delete(String login, String pwd) {
        if (!library.getReaderMap().containsKey(login)) {
            System.out.println("Пользователь с таким логином не зарегестрирован| " +
                    "User with such login is not registered");
        } else if (!library.getReaderMap().get(login).getPwd().equals(pwd)) {
            System.out.println("Введен неверный пароль| Wrong password");
        } else {
            library.getReaderMap().remove(login);
            System.out.println(pushData() ? "Читатель | Reader " + login + " удален | has been deleted" :
                    "По техническим причинам удалить читателя не удалось | " +
                            "Due to technical reasons, it was not possible to delete reader " + login);
        }
    }

    @Override
    public void update(String login, String oldPwd, String newPwd) {
        if (!library.getReaderMap().containsKey(login)) {
            System.out.println("Пользователь с таким логином не зарегестрирован| " +
                    "User with such login is not registered");
        } else if (!library.getReaderMap().get(login).getPwd().equals(oldPwd)) {
            System.out.println("Введен неверный пароль| Wrong password");
        } else if ((Pattern.compile("[^A-Za-z0-9]").matcher(newPwd).find())) {
            System.out.println("Введен некорректный пароль. Пароль должен состоять только из букв и цифр|" +
                    "Incorrect password. Password should contain only letters and digits");
        } else {
            library.getReaderMap().put(login, new Reader(login, newPwd));
            System.out.println(pushData() ? "Читатель | Reader " + login + " изменен | has been changed" :
                    "По техническим причинам изменить читателя не удалось | " +
                            "Due to technical reasons, it was not possible to change reader " + login);
        }
    }

    @Override
    public void addBook(String name, String author, int count) {
        if (library.getBookInfoMap().keySet().stream().anyMatch(s -> s.equals(name))) {
            library.getBookInfoMap().get(name).getBook().
                    setCount(library.getBookInfoMap().get(name).getBook().getCount() + count);
            System.out.println(pushData() ? "Информация о книге | Book info " + name + " обновлена | has been updated" :
                    "По техническим причинам обновить информацию о книге не удалось | " +
                            "Due to technical reasons, it was not possible to update book info " + name);
        } else {
            library.getBookInfoMap().
                    put(name, new BookInfo(new Book(name, author, count), true, new ArrayList<>()));
            System.out.println(pushData() ? "Книга | Book " + name + " добавлена | has been added" :
                    "По техническим причинам добавить книгу не удалось | " +
                            "Due to technical reasons, it was not possible to add book " + name);
        }
    }

    @Override
    public void getBook(String login, String pwd, String bookName) {
        if (library.getBookInfoMap().get(bookName).isFree()) {
            library.getBookInfoMap().get(bookName).getReaderList().add(library.getReaderMap().get(login));
            library.getBookInfoMap().get(bookName).updateAvailability();
            System.out.println(pushData() ? "Читатель | Reader " + login + " взял книгу | has taken book " + bookName :
                    "По техническим причинам взять книгу не удалось | " +
                            "Due to technical reasons, it was not possible to take book " + bookName);
        } else {
            System.out.println("Книга | Book " + bookName + " занята | is occupied");
        }
    }

    @Override
    public void returnBook(String login, String pwd, String bookName) {
        library.getBookInfoMap().get(bookName).getReaderList().remove(library.getReaderMap().get(login));
        library.getBookInfoMap().get(bookName).updateAvailability();
        System.out.println(pushData() ? "Читатель | Reader " + login + " вернул книгу | has returned book " + bookName :
                "По техническим причинам вернуть книгу не удалось | " +
                        "Due to technical reasons, it was not possible to return book " + bookName);
    }

    @Override
    public void print() {
        library.getReaderMap().entrySet().forEach(System.out::println);
        library.getBookInfoMap().entrySet().forEach(System.out::println);
    }

    @Override
    public boolean pushData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("Library.json"), library);
        } catch (IOException e) {
            System.out.println("Ошибка доступа, попробуйте позднее|Connection error, try again later");
            e.printStackTrace();
        }
        return true;

    }

    @Override
    public boolean pullData() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get("Library.json"));
            ObjectMapper objectMapper = new ObjectMapper();
            library = objectMapper.readValue(jsonData, Library.class);
        } catch (IOException e) {
            System.out.println("Ошибка доступа, попробуйте позднее|Connection error, try again later");
            e.printStackTrace();
        }
        return true;
    }
}
