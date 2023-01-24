package by.javaguru;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookInfo {
    private Book book;
    //    private LocalDate borrowDate;
    private boolean isFree;
    private List<Reader> readerList;

    public void updateAvailability() {
        setFree(book.getCount() > readerList.size());
    }
}
