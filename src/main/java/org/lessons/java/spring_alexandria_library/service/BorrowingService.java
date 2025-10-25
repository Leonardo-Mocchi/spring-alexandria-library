package org.lessons.java.spring_alexandria_library.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_alexandria_library.model.Borrowing;
import org.lessons.java.spring_alexandria_library.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;

    public List<Borrowing> findAll() {
        return borrowingRepository.findAll();
    }

    public List<Borrowing> findAllSortedByBorrowingDate() {
        return borrowingRepository.findAll(Sort.by("borrowingDate"));
    }

    public Borrowing create(Borrowing borrowing) {
        return borrowingRepository.save(borrowing);
    }

    public Borrowing update(Borrowing borrowing) {
        return borrowingRepository.save(borrowing);
    }

    public Borrowing getById(Integer id) {

        Optional<Borrowing> borrowingAttempt = borrowingRepository.findById(id);

        if (borrowingAttempt.isEmpty()) {
            /* throw new NameNotFoundException(); */
            //> lanciamo una not found con una response entity
        }

        return borrowingAttempt.get();
    }

    public void deleteById(Integer id) {

        borrowingRepository.deleteById(id);
    }

    public void delete(Borrowing borrowing) {

        deleteById(borrowing.getId());
    }

}
