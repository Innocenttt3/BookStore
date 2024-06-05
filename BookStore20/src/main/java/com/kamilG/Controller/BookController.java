package com.kamilG.Controller;

import com.kamilG.model.Book;
import com.kamilG.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/book")
public class BookController {

  @Autowired private BookService bookService;

  @RequestMapping(path = "/add", method = RequestMethod.GET)
  public String add(Model model) {
    model.addAttribute("book", new Book());
    return "bookForm";
  }

  @RequestMapping(path = "/add", method = RequestMethod.POST)
  public String add(@ModelAttribute Book book) {
    this.bookService.saveOrUpdateBook(book);
    return "redirect:/main";
  }

  @RequestMapping(path = "update/{id}", method = RequestMethod.GET)
  public String updateBook(@PathVariable long id, Model model) {
    Optional<Book> bookOptional = bookService.getBookById(id);
    if (bookOptional.isEmpty()) {
      return "redirect:/main";
    }
    model.addAttribute("book", bookOptional.get());
    return "bookForm";
  }

  @RequestMapping(path = "update/{id}", method = RequestMethod.POST)
  public String updateBook(@ModelAttribute Book book) {
    bookService.saveOrUpdateBook(book);
    return "redirect:/main";
  }
  @PostMapping("/delete")
  public String delete(@RequestParam long id) {
    bookService.deleteBookById(id);
    return "redirect:/main";
  }
}
