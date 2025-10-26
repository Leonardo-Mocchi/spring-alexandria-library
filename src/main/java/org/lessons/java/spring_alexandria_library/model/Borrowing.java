package org.lessons.java.spring_alexandria_library.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "borrowings")
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // , Libro da cui dipendo
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @JsonIgnore
    private Book book;

    @NotNull(message = "Borrowing Date cannot be null")
    @PastOrPresent(message = "Borrowing Date cannot be in the future")
    private LocalDate borrowingDate;

    @PastOrPresent(message = "Return Date cannot be in the future")
    private LocalDate returnDate;

    @Lob
    private String notes;

    public Borrowing() {
    }

    public Borrowing(Integer id, Book book, LocalDate borrowingDate, LocalDate returnDate, String notes) {
        this.id = id;
        this.book = book;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.notes = notes;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowingDate() {
        return this.borrowingDate;
    }

    public void setBorrowingDate(LocalDate borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", book='" + getBook() + "'" +
                ", borrowingDate='" + getBorrowingDate() + "'" +
                ", returnDate='" + getReturnDate() + "'" +
                ", notes='" + getNotes() + "'" +
                "}";
    }

}
