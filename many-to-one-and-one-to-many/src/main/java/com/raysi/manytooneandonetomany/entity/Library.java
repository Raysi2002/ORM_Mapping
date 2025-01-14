package com.raysi.manytooneandonetomany.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long libraryId;
    private String libraryName;

    @Builder.Default
    @OneToMany(
            mappedBy = "library", // Specifies that the 'library' field in Book owns the relationship.
            cascade = CascadeType.ALL, // Propagates all operations (persist, merge, remove, etc.) to associated Book entities.
            orphanRemoval = true, // Automatically removes Book entities when they are removed from the books list.
            fetch = FetchType.EAGER // Ensures associated Book entities are loaded immediately with Library.
    )
    private List<Book> books = new ArrayList<>();

    // Adds a single Book to the Library and sets the Library reference in the Book.
    public void addBook(Book book) {
        books.add(book);
        book.setLibrary(this);
    }

    // Adds multiple Books to the Library by iterating over the list.
    public void addBooks(List<Book> books) {
        books.forEach(this::addBook);
    }

    // Removes a single Book from the Library and clears the Library reference in the Book.
    public void removeBook(Book book) {
        books.remove(book);
        book.setLibrary(null);
    }

    // Removes multiple Books from the Library by iterating over the list.
    public void removeBooks(List<Book> books) {
        books.forEach(this::removeBook);
    }

    @Override
    public String toString() {
        // Custom string representation of Library including associated Books.
        return "Library Id: " + this.getLibraryId() +
                "\nLibrary Name: " + this.getLibraryName() +
                "\nBooks\n" + this.getBooks().toString();
    }
}
