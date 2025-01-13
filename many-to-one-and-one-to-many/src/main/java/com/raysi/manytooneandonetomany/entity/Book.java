package com.raysi.manytooneandonetomany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;
    private String bookName;
    private String authorName;

    @ManyToOne()
    @JoinColumn(name = "library_id")
    @JsonIgnore
    private Library library;
}
