package org.lessons.java.spring_alexandria_library.repository;

import org.lessons.java.spring_alexandria_library.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
