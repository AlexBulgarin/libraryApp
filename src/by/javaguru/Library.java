package by.javaguru;

import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Library {
    private Map<String, Reader> readerMap = new HashMap<>();
    private Map<String, List<BookInfo>> bookInfoMap = new HashMap<>();
}
