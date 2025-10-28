package org.lessons.java.spring_alexandria_library.controller;

import java.util.List;

import org.lessons.java.spring_alexandria_library.model.Book;
import org.lessons.java.spring_alexandria_library.model.Borrowing;
import org.lessons.java.spring_alexandria_library.repository.CategoryRepository;
import org.lessons.java.spring_alexandria_library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryRepository categoryRepository;

    /* @Autowired
    private BorrowingRepository borrowingRepository; */

    // #region READ - INDEX, PLURAL

    @GetMapping
    public String index(Authentication authentication, Model model) {

        List<Book> books = bookService.findAll(); // SELECT * FROM 'books' -> lista oggetti di tipo Book

        model.addAttribute("books", books);
        model.addAttribute("username", authentication.getName());
        return "books/index";
    }
    // #endregion

    // #region READ - SHOW, SINGULAR || also with custom filters
    @GetMapping("/{id}") // /books già specifdicato sopra in @RequestMapping!
    public String show(@PathVariable("id") Integer id, Model model) {

        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "books/show";
    }

    @GetMapping("/searchByTitle")
    public String searchByTitle(@RequestParam(name = "title") String title, Model model) {

        // List<Book> books = bookService.findByTitle(title);
        List<Book> books = bookService.findByTitle(title);

        model.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("/searchByTitleOrAuthor")
    public String searchByTitleOrAuthor(@RequestParam(name = "query") String query, Model model) {

        // List<Book> books = bookService.findByTitleOrAuthor(query, query);
        List<Book> books = bookService.findByTitleOrAuthor(query);

        model.addAttribute("books", books);
        return "books/index";
    }
    // #endregion

    // #region CREATE - CREATE + STORE

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "/books/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("book") Book formBook, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("categories", categoryRepository.findAll());
            return "/books/create";
        }

        // ? salvare il dato
        bookService.create(formBook);

        return "redirect:/books";
    }

    // #endregion

    // #region UPDATE - EDIT + UPDATE
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("categories", categoryRepository.findAll());

        model.addAttribute("book", bookService.getById(id));
        return "/books/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("book") Book formBook, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("categories", categoryRepository.findAll());
            return "/books/edit";
        }

        // ? aggiornamento del dato
        bookService.update(formBook);

        return "redirect:/books";
    }

    // #endregion

    // #region DELETE - DELETE, permanently remove from the database

    // ? ESSENDO UNA ROTTA POST AVRÒ BISOGNO DI UN FORM !!!
    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {

        // , prendere per ogni libro i prestiti che sono ad esso connessi -> getBorrowings()
        // * elimali dalla tabella borrowings -> borrowingRepository.delete(borrowing)
        // > a questo punto non ho più legami con borrrowings (vincoli di chiave esterna su book id) "book_id" della tabella borrowings

        Book book = bookService.getById(id);

        //, OPZIONALE IN CASO USO IL PARAMETRO CASCADE COME è SCRITTO NELLA ONETOMANY IN BOOK.JAVA
        /* 
        for (Borrowing borrowingToDelete : book.getBorrowings()) {
        borrowingRepository.delete(borrowingToDelete);
        } 
        */

        bookService.delete(book);

        return "redirect:/books";
    }

    // #endregion

    @GetMapping("/{id}/borrow")
    public String borrow(@PathVariable Integer id, Model model) {

        Borrowing borrowing = new Borrowing();

        borrowing.setBook(bookService.getById(id));

        model.addAttribute("borrowing", borrowing);
        return "/borrowings/create-or-edit";
    }

}
