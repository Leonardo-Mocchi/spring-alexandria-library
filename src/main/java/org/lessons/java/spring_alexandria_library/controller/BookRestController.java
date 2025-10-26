package org.lessons.java.spring_alexandria_library.controller;

import java.util.List;

import org.lessons.java.spring_alexandria_library.model.Book;
import org.lessons.java.spring_alexandria_library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    @Autowired
    private BookService bookService;

    //> INDEX
    @GetMapping
    public List<Book> index() {

        List<Book> books = bookService.findAll();
        return books;
    }

    //> SHOW
    @GetMapping("/{id}")
    public Book show(@PathVariable Integer id) {

        Book book = bookService.getById(id);
        return book;
    }

    //> STORE
    @PostMapping
    public Book store(@RequestBody Book book) {

        return bookService.create(book);
    }

    //> UPDATE
    @PutMapping("/{id}")
    public Book update(@PathVariable Integer id, @RequestBody Book book) {

        book.setId(id);
        return bookService.update(book);
    }

    //> DELETE  
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        bookService.deleteById(id);
    }

}
