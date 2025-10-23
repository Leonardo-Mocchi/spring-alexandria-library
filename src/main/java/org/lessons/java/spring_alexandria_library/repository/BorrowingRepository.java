package org.lessons.java.spring_alexandria_library.repository;

import org.lessons.java.spring_alexandria_library.model.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRepository extends JpaRepository<Borrowing, Integer> {

}
