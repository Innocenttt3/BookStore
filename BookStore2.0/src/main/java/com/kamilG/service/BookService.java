package com.kamilG.service;

import com.kamilG.model.Book;
import com.kamilG.repository.BookRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService {

  @Autowired private BookRepository bookRepository;

  @Override
  public void saveOrUpdateBook(Book book) {
    System.out.println(book);
    bookRepository.save(book);
  }

  @Override
  @Transactional
  public Optional<Book> getBookById(long id) {
    return bookRepository.findById(id);
  }

  @Override
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  @Override
  public void deleteBookById(long id) {
    bookRepository.deleteById(id);
  }
}
