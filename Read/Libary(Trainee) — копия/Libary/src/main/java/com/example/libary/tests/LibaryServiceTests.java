package com.example.libary.tests;
import com.example.libary.Entity.bookLibary;
import com.example.libary.Entity.bookState;
import com.example.libary.Entity.BookStateDto;
import com.example.libary.repository.BookRepository;
import com.example.libary.repository.BookStateRepository;
import com.example.libary.service.LibaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LibaryServiceTests {
    @Mock
    private BookStateRepository bookStateRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LibaryService libaryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Инициализация моков
    }

    @Test
    void testGetBooksWithStat_WhenBooksAndStatesExist() {
        // Подготовка данных
        bookLibary book1 = new bookLibary();
        book1.setId(1L);
        bookLibary book2 = new bookLibary();
        book2.setId(2L);

        bookState state1 = new bookState();
        state1.setBookId(1L);
        state1.setBorrowedAt(LocalDateTime.now().minusDays(1));
        state1.setReturnDue(LocalDateTime.now().plusDays(5));

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));
        when(bookStateRepository.findAll()).thenReturn(Collections.singletonList(state1));

        // Вызов метода
        List<BookStateDto> result = libaryService.getBooksWithStat();

        // Проверка результата
        assertEquals(2, result.size());
        assertEquals("Занята", result.get(0).getStatus());
        assertEquals("Свободная", result.get(1).getStatus());
    }

    @Test
    void testGetBooksWithStat_WhenNoBooksExist() {
        // Настройка для пустого списка книг
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());
        when(bookStateRepository.findAll()).thenReturn(Collections.emptyList());

        // Вызов метода
        List<BookStateDto> result = libaryService.getBooksWithStat();

        // Проверка результата
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetBookStateById_WhenExists() {
        // Подготовка данных
        bookState state = new bookState();
        state.setBookId(1L);

        when(bookStateRepository.findById(1L)).thenReturn(Optional.of(state));

        // Вызов метода
        bookState result = libaryService.getBookStateById(1L);

        // Проверка результата
        assertNotNull(result);
        assertEquals(1L, result.getBookId());
    }

    @Test
    void testGetBookStateById_WhenNotExists() {
        // Настройка для случая, когда книга не найдена
        when(bookStateRepository.findById(1L)).thenReturn(Optional.empty());

        // Вызов метода
        bookState result = libaryService.getBookStateById(1L);

        // Проверка результата
        assertNull(result);
    }

}
