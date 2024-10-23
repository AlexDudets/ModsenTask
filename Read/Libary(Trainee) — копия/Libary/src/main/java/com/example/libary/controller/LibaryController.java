package com.example.libary.controller;
import org.springframework.ui.Model;
import com.example.libary.Entity.BookStateDto;
import com.example.libary.service.LibaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.libary.Entity.bookState;
import org.springframework.web.bind.annotation.PostMapping;



import java.util.List;

@Controller
public class LibaryController {
    private final LibaryService libaryService;

    @Autowired
    public LibaryController(LibaryService libaryService) {
        this.libaryService = libaryService;
    }

    @GetMapping("/bookstatus")
    public String getAllBooksWithStat(Model model) {
        List<BookStateDto> bookStateDtoList = libaryService.getBooksWithStat();
        model.addAttribute("books", bookStateDtoList);
        return "bookstatus";
    }

//    @GetMapping("/edit/{id}")
//    public String editBookState(@PathVariable Long id, Model model) {
//        bookState state = libaryService.getBookStateById(id);
//        if (state != null) {
//            BookStateDto bookStateDto = new BookStateDto();
//            bookStateDto.setId(state.getBookId());
//            bookStateDto.setBorrowedAt(state.getBorrowedAt());
//            bookStateDto.setReturnDue(state.getReturnDue());
//            model.addAttribute("bookStateDto", bookStateDto);
//        }
//        return "editBookState";
//
//    }
//    @PostMapping("/update")
//    public String updateBookState(@ModelAttribute BookStateDto bookStateDto){
//
//        return "bookstatus";
//    }
}