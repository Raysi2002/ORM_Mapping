package com.raysi.manytooneandonetomany.repository;

import com.raysi.manytooneandonetomany.entity.Book;
import com.raysi.manytooneandonetomany.entity.Library;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class LibraryRepositoryTest {
    @Autowired
    private LibraryRepository libraryRepository;

    @Test
    public void saveData() {
        // Create a Book entity with author name and book name.
        Book book = Book.builder()
                .authorName("Preeti")
                .bookName("Our Journey")
                .build();

        // Add the Book to a list.
        List<Book> books = new ArrayList<>();
        books.add(book);

        // Create a Library entity and associate the list of Books with it.
        Library library = Library.builder()
                .libraryName("Ray's Learning")
                .build();
        library.addBooks(books); // Establish the relationship.

        libraryRepository.save(library); // Save the Library, cascading the save operation to associated Books.
    }

    @Test
    public void fetchBooks() {
        // Fetch all Library entities along with their associated Books.
        List<Library> books = libraryRepository.findAll();
        System.out.println(books); // Print the result.
    }

    @Test
    public void deleteBook() {
        // Delete a Library by its ID. Cascade ensures associated Books are also deleted.
        libraryRepository.deleteById(1L);
    }
}