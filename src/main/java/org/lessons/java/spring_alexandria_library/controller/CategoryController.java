package org.lessons.java.spring_alexandria_library.controller;

import org.lessons.java.spring_alexandria_library.model.Category;
import org.lessons.java.spring_alexandria_library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // * INDEX
    @GetMapping
    public String index(Model model) {

        model.addAttribute("categories", categoryService.findAll());
        return "categories/index";
    }

    // * SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("category", categoryService.getById(id));
        return "categories/show";
    }

    // * CREATE
    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("category", new Category());
        return "categories/create-or-edit";
    }

    // * STORE
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("category") Category formCategory, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "categories/create-or-edit";
        }
        categoryService.create(formCategory);
        return "redirect:/categories";
    }

    // * EDIT
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("category", categoryService.getById(id));
        model.addAttribute("edit", true);
        return "categories/create-or-edit";
    }

    // * UPDATE
    @PostMapping("/edit/{id}")
    public String edit(@Valid @ModelAttribute("category") Category formCategory, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "categories/create-or-edit";
        }
        categoryService.update(formCategory);
        return "redirect:/categories";
    }

    // * DELETE
    @PostMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {

        categoryService.deleteById(id);

        return "redirect:/categories";
    }
}
