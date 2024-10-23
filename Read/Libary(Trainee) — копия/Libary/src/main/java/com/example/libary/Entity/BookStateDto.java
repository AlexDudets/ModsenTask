package com.example.libary.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



@Data
public class BookStateDto {
    private Long id;
    private LocalDateTime borrowedAt;
    private LocalDateTime returnDue;
    private String status;

    public String getFormattedBorrowedAt() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return (borrowedAt != null) ? borrowedAt.format(formatter) : "Нет данных";
    }

    public String getFormattedReturnDue() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return (returnDue != null) ? returnDue.format(formatter) : "Нет данных";
    }
}
