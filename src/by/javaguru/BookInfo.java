package by.javaguru;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookInfo {
    private Book book;
//    private LocalDate borrowDate;
    private boolean isFree;
    private Reader reader;
}
