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
            mappedBy = "library",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book){
        books.add(book);
        book.setLibrary(this);
    }

    public void addBooks(List<Book> books){
        books.forEach(this :: addBook);
    }

    public void removeBook(Book book){
        books.remove(book);
        book.setLibrary(null);
    }

    public void removeBooks(List<Book> books){
        books.forEach(this :: removeBook);
    }

    @Override
    public String toString() {
        return "Library Id: " + this.getLibraryId() +
                "\nLibrary Name: " + this.getLibraryName() +
                "\nBook\n" + this.getBooks().toString();
    }
}
