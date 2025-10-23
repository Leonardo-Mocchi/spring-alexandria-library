package org.lessons.java.spring_alexandria_library.controller;

import org.lessons.java.spring_alexandria_library.model.Book;
import org.lessons.java.spring_alexandria_library.model.Borrowing;
import org.lessons.java.spring_alexandria_library.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/borrowings")
public class BorrowingController {

    @Autowired
    private BorrowingRepository repository;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("borrowing") Borrowing formBorrowing, BindingResult bindingresult,
            Model model) {

        if (bindingresult.hasErrors()) {
            return "/borrowings/create-or-edit";
        }

        repository.save(formBorrowing);

        return "redirect:/books/" + formBorrowing.getBook().getId();
    }

    // , metodo che restituisca una edit (con dati già inseriti)
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("borrowing", repository.findById(id).get());
        model.addAttribute("edit", true);
        return "borrowings/create-or-edit";
    }

    // ? metodo che effettui una update vera e propria (con validazione)
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("borrowing") Borrowing formBorrowing, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "/borrowings/create-or-edit";
        }

        repository.save(formBorrowing);

        return "redirect:/books/" + formBorrowing.getBook().getId();

    }

}
