package com.busanit501.helloworld.book;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum BookService {
    INTERFACE;

    public List<BookDTO> getBooks() {
        List<BookDTO> books = IntStream.range(0,10).mapToObj(
                i -> {
                    BookDTO book = new BookDTO();
                    book.setBookID(i);
                    book.setBookName("Book Name "+i);
                    book.setAuthor("Author "+i);
                    return book;
                }
        ).collect(Collectors.toList());
        return books;
    }

    public BookDTO getBook(int bookID) {
        return getBooks().get(bookID);
    }
}
