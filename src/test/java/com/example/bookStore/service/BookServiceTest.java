package com.example.bookStore.service;

import com.example.bookStore.dto.BookDto;
import com.example.bookStore.model.Book;
import com.example.bookStore.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ModelMapper mapper;

    @Test
    void shouldFetchListOfBookDtos() {
        List<Book> books = new ArrayList<>();
        Book book = new Book(UUID.randomUUID(), "Kanni theevu", "Crime", 1987);
        books.add(book);
        when(bookRepository.findAll()).thenReturn(books);
        BookDto bookDto = new BookDto(UUID.randomUUID(), "Kanni theevu", "Crime", 1987);
        when(mapper.map(book, BookDto.class)).thenReturn(bookDto);
        List<BookDto> bookDtos = bookService.getBookDtos();
        assertThat(bookDtos.size(), is(equalTo(1)));
        assertThat(bookDtos.get(0))
                .isNotNull()
                .hasFieldOrPropertyWithValue("title", "Kanni theevu")
                .hasFieldOrPropertyWithValue("description", "Crime")
                .hasFieldOrPropertyWithValue("releaseYear", 1987);
    }
}
