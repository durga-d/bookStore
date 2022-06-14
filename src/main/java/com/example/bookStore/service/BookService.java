package com.example.bookStore.service;

import com.example.bookStore.dto.BookDto;
import com.example.bookStore.model.Book;
import com.example.bookStore.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService {

    private BookRepository bookRepository;
    private ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, ModelMapper mapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = mapper;
    }

    public List<BookDto> getBookDtos() {
        Iterable<Book> all = bookRepository.findAll();
        return StreamSupport.stream(all.spliterator(),false)
                .map(convertBookToBookDto())
                .collect(Collectors.toList());
    }

    private Function<Book, BookDto> convertBookToBookDto() {
        return book -> modelMapper.map(book, BookDto.class);
    }
}
