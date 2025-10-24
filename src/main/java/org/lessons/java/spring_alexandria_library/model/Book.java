package org.lessons.java.spring_alexandria_library.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "books") // opzionale il campo tra parentesi in caso la tabella abbia un nome uguale
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // oltre IDENTITY ci sono altri tipi!
    private Integer id;

    @Size(min = 13, max = 13, message = "ISBN must be 13 characters long") // message aggiungibile ad ogni regola
    @Column(name = "isbn_code", length = 13, nullable = false) // name sets the name of the column inside the db
    @NotBlank(message = "ISBN must not be null, nor empty or blank")
    private String isbn;

    // @NotBlank NO contenuto vuoto(anche spazi) O NULL utile per String
    // @NotEmpty controlla che la dimensione sia ALMENO superiore a 0 e no NULL
    // @NotNull i valori non possono essere null

    @NotBlank(message = "Title must not be null, nor empty or blank")
    private String title;

    @NotBlank(message = "Author must not be null, nor empty or blank")
    private String author;

    @NotBlank(message = "Publisher must not be null, nor empty or blank")
    private String publisher;

    @Lob // > lob = Long OBject
    private String synopsis;

    @NotNull(message = "Publication date must not be null")
    private LocalDate publicationDate;

    @NotNull(message = "Number of copies must not be null")
    @Min(value = 0, message = "The number of copies cannot be negative")
    private Integer numberOfCopies;

    // , aggiunta di una relazione tra UN LIBRO e 0, 1 o più prestiti
    @OneToMany(mappedBy = "book", cascade = { CascadeType.REMOVE })
    private List<Borrowing> borrowings;

    @ManyToMany
    @JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Borrowing> getBorrowings() {
        return this.borrowings;
    }

    public void setBorrowings(List<Borrowing> borrowings) {
        this.borrowings = borrowings;
    }

    public Book() {
    }

    public Book(Integer id, String isbn, String title, String author, String publisher, String synopsis,
            LocalDate publicationDate, Integer numberOfCopies) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.synopsis = synopsis;
        this.publicationDate = publicationDate;
        this.numberOfCopies = numberOfCopies;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSynopsis() {
        return this.synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public LocalDate getPublicationDate() {
        return this.publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getNumberOfCopies() {
        return this.numberOfCopies;
    }

    public void setNumberOfCopies(Integer numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    @Override
    public String toString() {
        return String.format("%s: %s, code: %s, published by %s", this.author, this.title, this.isbn, this.publisher);
    }
}