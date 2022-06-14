package com.example.bookStore.controller;

import com.example.bookStore.dto.BookDto;
import com.example.bookStore.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Test
    void shouldFetchAllBookDtos(){
        List<BookDto> bookDtos = new ArrayList<>();
        BookDto bookDto = new BookDto(UUID.randomUUID(), "Kanni theevu", "Crime", 1987);
        bookDtos.add(bookDto);
        when(bookService.getBookDtos()).thenReturn(bookDtos);
        ResponseEntity<List<BookDto>> books = bookController.getBooks();
        assertThat(books.getBody()).isNotNull();
        assertThat(books.getBody().size()).isEqualTo(1);
        assertThat(books.getBody().get(0))
                .isNotNull()
                .hasFieldOrPropertyWithValue("title", "Kanni theevu")
                .hasFieldOrPropertyWithValue("description", "Crime")
                .hasFieldOrPropertyWithValue("releaseYear", 1987);
    }
}
