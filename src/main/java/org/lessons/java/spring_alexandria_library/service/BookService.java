package org.lessons.java.spring_alexandria_library.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_alexandria_library.model.Book;
import org.lessons.java.spring_alexandria_library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findAllSortedByTitle() {
        return bookRepository.findAll(Sort.by("title"));
    }

    public List<Book> findAllSortedByAuthor() {
        return bookRepository.findAll(Sort.by("author"));
    }

    public Book getById(Integer id) {

        //? gestire casistica in cui non viene trovata la risorsa

        Optional<Book> bookAttempt = bookRepository.findById(id);

        if (bookAttempt.isEmpty()) {
            /* throw new NameNotFoundException(); */
            //> lanciamo una not found con una response entity
        }

        return bookAttempt.get();
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> findByTitleOrAuthor(String query) {
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);
    }

    public Book create(Book book) {
        //? aggiornamento solo di alcuni campi a segguito della creazione
        return bookRepository.save(book);
    }

    public Book update(Book book) {
        //? aggiornamento di altri campi a segguito dell'aggiornamento

        return bookRepository.save(book);
    }

    public void delete(Book book) {

        //, OPZIONALE IN CASO USO IL PARAMETRO CASCADE COME è SCRITTO NELLA ONETOMANY IN BOOK.JAVA
        /* 
        for (Borrowing borrowingToDelete : book.getBorrowings()) {
        borrowingRepository.delete(borrowingToDelete);
        } 
        */

        bookRepository.delete(book);
    }

    public void deleteById(Integer id) {

        Book book = getById(id);

        //, OPZIONALE IN CASO USO IL PARAMETRO CASCADE COME è SCRITTO NELLA ONETOMANY IN BOOK.JAVA
        /* 
        for (Borrowing borrowingToDelete : book.getBorrowings()) {
        borrowingRepository.delete(borrowingToDelete);
        } 
        */

        bookRepository.delete(book);
    }

    public Boolean existsById(Integer id) {
        return bookRepository.existsById(id);
    }

    public Boolean exists(Book book) {
        return existsById(book.getId());
    }
}
