package csd230.lab1.ShawnHaman.pojos;

import java.util.Objects;

/**
 * DTO for {@link csd230.lab1.ShawnHaman.entities.Book}
 */
public class Book extends Publication {
    private String author;
    private String ISBN;
    //    private String ISBN_10;
    public Book() {}

    public Book(double price, int quantity, String description, Cart cart, String title, int copies, String author, String ISBN) {
        super(price, quantity, description, cart, title, copies);
        this.author = author;
        this.ISBN = ISBN;
    }

    // 1.Override toString
    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", " + super.toString() +
                '}';
    }

    // 2. Override equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(author, book.author) && Objects.equals(ISBN, book.ISBN);
    }

    // 3.Override hashCode
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, ISBN);
    }

    // Getters and Setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}


