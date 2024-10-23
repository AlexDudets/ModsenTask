package com.example.libary.repository;
import com.example.libary.Entity.bookState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.libary.Entity.bookLibary;

import java.time.LocalDateTime;
import java.util.List;

public interface BookStateRepository extends JpaRepository<bookState,Long> {

}
