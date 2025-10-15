package org.lessons.java.spring_alexandria_library.repository;

import java.util.List;

import org.lessons.java.spring_alexandria_library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    // public List<Book> findByTitle(String title);

    public List<Book> findByTitleContaining(String title);

    // public List<Book> findByTitleOrAuthor(String title, String author);

    public List<Book> findByTitleContainingOrAuthorContaining(String title, String author);

}
