package com.example.libary.repository;
import com.example.libary.Entity.bookLibary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<bookLibary,Long> {
    bookLibary findByIsbn(String isbn);
}
