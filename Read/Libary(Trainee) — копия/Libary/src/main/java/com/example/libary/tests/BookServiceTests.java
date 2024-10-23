package com.example.libary.tests;

import com.example.libary.Entity.bookLibary;
import com.example.libary.repository.BookRepository;
import com.example.libary.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    private bookLibary book;

    @BeforeEach
    void setUp() {
        book = new bookLibary();
        book.setId(1L);
        book.setName("Spring");
        book.setIsbn("123456789");
    }

    @Test
    void testFindAll() {

        List<bookLibary> books = Arrays.asList(book); // Создание списка с одной книгой
        when(bookRepository.findAll()).thenReturn(books); // Настройка мока

        // Вызов метода
        List<bookLibary> result = bookService.findAll(); // Вызов findAll

        // Проверка результата
        assertEquals(1, result.size()); // Проверка размера списка
        assertEquals("Spring", result.get(0).getName()); // Проверка названия
    }

    @Test
    void testFindById_BookFound(){
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        bookLibary result = bookService.findById(1L);

        assertNotNull(result);
        assertNotNull("Spring", result.getName());
    }

    @Test
    void testFindById_BookNotFound(){
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        bookLibary result = bookService.findById(1L);

        assertNull(result);
    }

    @Test
    void testFindByIsbn_BookFound(){
        when(bookRepository.findByIsbn(any())).thenReturn(book);
        bookLibary result = bookService.findByISBN("123456789");
        assertNotNull(result);
        assertEquals("Spring",result.getName());
    }
    @Test
    void  testFindByIsbn_BookNotFound(){
        when(bookRepository.findByIsbn(any())).thenReturn(null);
        bookLibary result = bookService.findByISBN("123456789");
        assertNull(result);
    }

    @Test
    void testSave(){
        when(bookRepository.save(any())).thenReturn(book);
        bookLibary result = bookService.save(book);
        assertNotNull(result);
        assertEquals("Spring",result.getName());
    }
    @Test
    void testDelete(){
       bookService.delete(1L);

       verify(bookRepository).deleteById(1L);
    }
    @Test
    void testUpdateBook(){

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        when(bookRepository.save(any())).thenReturn(book);


        bookLibary result = bookService.save(book);

        // Проверка, что результат не null и имеет правильные значения
        assertNotNull(result);
        assertEquals("Spring", result.getName());

        // Проверка, что метод save был вызван один раз
        verify(bookRepository, times(1)).save(book);


    }
}
