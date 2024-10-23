package com.example.libary.service;
import com.example.libary.Entity.bookLibary;
import com.example.libary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<bookLibary> findAll() {

        return bookRepository.findAll();
    }

    public bookLibary findById(Long id) {

        return bookRepository.findById(id).orElse(null);
    }

    public bookLibary findByISBN(String isbn) { // Изменено

        return bookRepository.findByIsbn(isbn);
    }

    public bookLibary save(bookLibary book) {

        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

//    public bookLibary updateBook(Long id, bookLibary book) {
//        bookLibary Book = bookRepository.findById(id).orElse(null); // Находим существующую книгу
//        if (Book != null) {
//            // Обновляем поля существующей книги
//            Book.setIsbn(book.getIsbn());
//            Book.setName(book.getName());
//            Book.setGenre(book.getGenre());
//            Book.setDescription(book.getDescription());
//            Book.setAuthor(book.getAuthor());
//
//            // Сохраняем обновленную книгу и возвращаем её
//            return bookRepository.save(Book); // Сохраняем только один раз
//        }
//        return null; // Если книга не найдена, возвращаем null
//    }
}

