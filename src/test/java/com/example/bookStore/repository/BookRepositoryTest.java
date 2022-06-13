package com.example.bookStore.repository;

import com.example.bookStore.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void shouldFetchAllBooksInDB() {
        Book book = new Book(UUID.randomUUID(),"Selvan", "Historic novel", 2022);
        bookRepository.save(book);
        Iterable<Book> allBooks = bookRepository.findAll();
        Long bookCount = StreamSupport.stream(allBooks.spliterator(),false).count();
        assertEquals(bookCount,2);
    }

    @Test
    void shouldReturnNoOfBooksWithTitlePonniyin(){
        List<Book> ponniyinBooks = bookRepository.findByTitle("Ponniyin");
        assertEquals(ponniyinBooks.size(), 1);
    }
}
