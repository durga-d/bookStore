package com.example.bookStore.repository;

import com.example.bookStore.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends CrudRepository<Book, UUID> {

    List<Book> findByTitle(String title);
}
