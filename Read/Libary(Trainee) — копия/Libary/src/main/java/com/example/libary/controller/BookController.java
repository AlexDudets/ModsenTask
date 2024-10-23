package com.example.libary.controller;


import com.example.libary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.libary.Entity.bookLibary;


import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String findAll(Model model) {
        List<bookLibary> bookLibraries = bookService.findAll();
        model.addAttribute("Books", bookLibraries);
        return "booklist";
    }

    @GetMapping("/AddBooks")
    public String addBooksForm(Model model) {
        model.addAttribute("book", new bookLibary());
        return "AddBooks";
    }

    @PostMapping("/save")
    public String addBooks(bookLibary book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
    @GetMapping("/getid{id}")
    public String getIdForm(Model model){
        model.addAttribute("book",null);
        return "getid";
    }
    @PostMapping("/getid{id}")
    public String getId(@RequestParam Long id, Model model){
        bookLibary book = bookService.findById(id);
        if (book != null) {
            model.addAttribute("book", book);
        } else {
            model.addAttribute("errorMessage", "Книга не найдена.");
        }
        return "getid";
    }
    @GetMapping("/getisbn{isbn}")
    public String getIsbnForm(Model model){
        model.addAttribute("book",null);
        return "getisbn";
    }
    @PostMapping("/getisbn{isbn}")
    public String getIsbn(String isbn, Model model){
        bookLibary book = bookService.findByISBN(isbn);
        if (book != null) {
            model.addAttribute("book", book);
        } else {
            model.addAttribute("errorMessage","Книга не найдена");

        }
        return "getisbn";
    }
    @GetMapping("/books/edit/{id}")
    public String editBook(@PathVariable Long id, Model model){
        bookLibary book = bookService.findById(id);
        model.addAttribute("book", book);
        return "editbook";
    }
    @PostMapping("/books/update/{id}")
    public String updateBook(@PathVariable Long id,@ModelAttribute bookLibary book){
        bookService.save(book);
        return "redirect:/books";
    }

//    @GetMapping("/books/edit/{id}")
//    public String editForm(@PathVariable Long id, Model model){
//        // Получение книги по ID
//        bookLibary book = bookService.findById(id);
//        if (book == null) {
//            return null;
//        }
//
//        model.addAttribute("book", book);
//        return "editbook";
//    }
//                                                                 //400 статус, разобраться с сервисом и тут
//
//    @PostMapping("/books/update/{id}")
//    public String update(@PathVariable Long id,@ModelAttribute bookLibary book){
//        bookLibary updatedBook = bookService.updateBook(id, book);
//        if (updatedBook == null) {
//            return null;
//        }
//        return "/books";
//
//    }
}





