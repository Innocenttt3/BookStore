package com.kamilG.service;

import com.kamilG.model.Book;
import java.util.List;
import java.util.Optional;

public interface IBookService {
  void saveOrUpdateBook(Book book);

  Optional<Book> getBookById(long id);

  List<Book> getAllBooks();

  void deleteBookById(long id);
}
