package org.lessons.java.spring_alexandria_library.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_alexandria_library.model.Book;
import org.lessons.java.spring_alexandria_library.model.Category;
import org.lessons.java.spring_alexandria_library.repository.BookRepository;
import org.lessons.java.spring_alexandria_library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<Category> findAllSortedByName() {
        return categoryRepository.findAll(Sort.by("name"));
    }

    public Category getById(Integer id) {

        // ? gestire casistica in cui non viene trovata la risorsa

        Optional<Category> categoryAttempt = categoryRepository.findById(id);

        if (categoryAttempt.isEmpty()) {
            /* throw new NameNotFoundException(); */
            // > lanciamo una not found con una response entity
        }

        return categoryAttempt.get();
    }

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Category category) {

        // Remove this category from any linked books to avoid constraint issues
        if (category.getBooks() != null) {
            for (Book linkedBook : category.getBooks()) {
                linkedBook.getCategories().remove(category);
                bookRepository.save(linkedBook);
            }
        }

        categoryRepository.delete(category);
    }

    public void deleteById(Integer id) {

        Category category = getById(id);

        if (category.getBooks() != null) {
            for (Book linkedBook : category.getBooks()) {
                linkedBook.getCategories().remove(category);
                bookRepository.save(linkedBook);
            }
        }

        categoryRepository.delete(category);
    }

    public Boolean existsById(Integer id) {
        return categoryRepository.existsById(id);
    }

    public Boolean exists(Category category) {
        return existsById(category.getId());
    }

}
