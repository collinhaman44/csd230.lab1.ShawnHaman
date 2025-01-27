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

}

