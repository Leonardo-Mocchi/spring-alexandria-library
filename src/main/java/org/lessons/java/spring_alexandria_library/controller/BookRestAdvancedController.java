package org.lessons.java.spring_alexandria_library.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_alexandria_library.model.Book;
import org.lessons.java.spring_alexandria_library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/books")
public class BookRestAdvancedController {

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
    public ResponseEntity<Book> show(@PathVariable Integer id) {

        Optional<Book> bookAttempt = bookService.findById(id);

        //, handles not finding book with given id
        if (bookAttempt.isEmpty()) {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Book>(bookAttempt.get(), HttpStatus.OK);
    }

    //> STORE
    @PostMapping
    public ResponseEntity<Book> store(@RequestBody Book book) {

        return new ResponseEntity<Book>(bookService.create(book), HttpStatus.OK);
    }

    //> UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Integer id, @RequestBody Book book) {

        if (bookService.findById(id).isEmpty()) {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
        book.setId(id);
        return new ResponseEntity<Book>(bookService.update(book), HttpStatus.OK);
    }

    //> DELETE  
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> delete(@PathVariable Integer id) {
        if (bookService.findById(id).isEmpty()) {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }

        bookService.deleteById(id);
        return new ResponseEntity<Book>(HttpStatus.OK);
    }

}
