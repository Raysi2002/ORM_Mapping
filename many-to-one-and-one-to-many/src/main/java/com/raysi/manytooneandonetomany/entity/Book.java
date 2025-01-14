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

    @ManyToOne // Specifies the many-to-one relationship with Library.
    @JoinColumn(
            name = "library_id", // Name of the foreign key column in the Book table.
            referencedColumnName = "libraryId" // References the primary key in the Library table.
    )
    @JsonIgnore // Prevents cyclic serialization issues during JSON serialization.
    private Library library;

    @Override
    public String toString() {
        // Custom string representation of Book.
        return "Book Id: " + this.getBookId() +
                "\nBook Name: " + this.getBookName() +
                "\nAuthor Name: " + this.getAuthorName();
    }
}
