package com.kamilG.Controller;

import com.kamilG.model.Book;
import com.kamilG.service.BookService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {

  @Autowired private BookService bookService;

  @GetMapping("/add")
  public String addBookForm(Model model) {
    model.addAttribute("book", new Book());
    return "bookForm";
  }

  @PostMapping("/add")
  public String addBook(@ModelAttribute Book book) {
    bookService.saveOrUpdateBook(book);
    return "redirect:/main";
  }

  @GetMapping("/update/{id}")
  public String updateBookForm(@PathVariable long id, Model model) {
    Optional<Book> bookOptional = bookService.getBookById(id);
    if (bookOptional.isEmpty()) {
      return "redirect:/main";
    }
    model.addAttribute("book", bookOptional.get());
    return "bookForm";
  }

  @PostMapping("/update/{id}")
  public String updateBook(@ModelAttribute Book book) {
    bookService.saveOrUpdateBook(book);
    return "redirect:/main";
  }

  @PostMapping("/delete")
  public String deleteBook(@RequestParam long id) {
    bookService.deleteBookById(id);
    return "redirect:/main";
  }
}
