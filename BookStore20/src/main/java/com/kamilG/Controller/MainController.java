package com.kamilG.Controller;

import com.kamilG.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @RequestMapping(path = {"/main", "/", "/index"}, method = RequestMethod.GET)
    public String displayBookList(Model model) {
        model.addAttribute("books", this.bookService.getAllBooks());
        return "bookList";
    }
}
