package by.javaguru;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LibraryController implements ILibraryApplication{

    public static Library library = new Library();

    @Override
    public String add(String login, String pwd) {
        //TODO verify login and pwd
        library.getReaderMap().put(login, new Reader(login, pwd));
        String result = pushData() ? "Читателю " + login + " выдан читательский билет" :
                "По техническим причинам выдать читательский билет не получилось " + login;
        return result;
    }

    @Override
    public String getBook(String login, String pwd, String bookName) {
        return null;
    }

    @Override
    public boolean pushData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("Library.json"), library);
        } catch (IOException e) {
            //TODO свое runtime исключение
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
            //TODO свое исключение
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void print() {
        library.getReaderMap().entrySet().forEach(System.out::println);
    }

    @Override
    public void addBook(String name, String author, int count) {
        //TODO проверить или уже есть такая книга и добавить в список иначе сделать put
    }

}
