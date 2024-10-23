package com.example.libary.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "book_state")
public class bookState {

   @ManyToOne
   @JoinColumn(name = "book_id",referencedColumnName = "id",insertable = false,updatable = false)
   private bookLibary book;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id",nullable = false)
    private Long bookId;

    @Column(name = "borrowed_at", nullable = false)
    private LocalDateTime borrowedAt;

    @Column(name = "return_due", nullable = false)
    private LocalDateTime returnDue; // срок возврата

}
