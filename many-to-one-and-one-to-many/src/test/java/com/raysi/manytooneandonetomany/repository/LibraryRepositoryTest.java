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
    public void saveData(){

        Book book = Book.builder()
                .authorName("Preeti")
                .bookName("Our Journey")
                .build();
        List<Book> books = new ArrayList<>();
        books.add(book);

        Library library = Library.builder()
                .libraryName("Ray's Learning")
                .build();
        library.addBooks(books);
        libraryRepository.save(library);
    }

    @Test
    public void fetchBooks(){
        List<Library> books = libraryRepository.findAll();
        System.out.println(books);
    }

    @Test
    public void deleteBook(){
        libraryRepository.deleteById(1L);
    }
}