package com.example.libary.service;

import com.example.libary.Entity.bookLibary;
import com.example.libary.Entity.bookState;
import com.example.libary.Entity.BookStateDto;
import com.example.libary.repository.BookStateRepository;
import com.example.libary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LibaryService {

    private final BookStateRepository bookStateRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LibaryService(BookStateRepository bookStateRepository, BookRepository bookRepository) {
        this.bookStateRepository = bookStateRepository;
        this.bookRepository = bookRepository;
    }

    public List<BookStateDto> getBooksWithStat() {
        List<bookLibary> allBooks = bookRepository.findAll();
        List<bookState> allStates = bookStateRepository.findAll();


        Map<Long, bookState> stateMap = new HashMap<>();
        for (bookState bs : allStates) {
            stateMap.put(bs.getBookId(), bs);
        }

        List<BookStateDto> bookInfoList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (bookLibary book : allBooks) {
            BookStateDto info = new BookStateDto();
            info.setId(book.getId());

            // Состояни из мапа
            bookState state = stateMap.get(book.getId());

            if (state != null) {
                info.setBorrowedAt(state.getBorrowedAt());
                info.setReturnDue(state.getReturnDue());
                info.setStatus(state.getReturnDue().isAfter(now) ? "Занята" : "Свободная");
            } else {
                info.setBorrowedAt(null);
                info.setReturnDue(null);
                info.setStatus("Свободная");
            }

            bookInfoList.add(info);
        }

        return bookInfoList;
    }

    public bookState getBookStateById(Long id) {
        return bookStateRepository.findById(id).orElse(null);
    }
}
//
//    public void saveBookState(BookStateDto bookStateDto) {
//        bookState state = new bookState();
//        state.setBookId(bookStateDto.getId());
//        state.setBorrowedAt(bookStateDto.getBorrowedAt());
//        state.setReturnDue(bookStateDto.getReturnDue());
//
//        bookStateRepository.save(state);
//    }
//    public bookState update(Long id, BookStateDto bookStateDto){
//
//
//    }
//
//}
