package csd230.lab1.ShawnHaman.repositories;

import csd230.lab1.ShawnHaman.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Example Queries given in the instructions
    List<Book> findByIsbn(String ISBN);
    Book findById(long id);
    List<Book> findByTitle(String title);

    // Return books within a price range
    @Query("SELECT b FROM Book b WHERE b.price BETWEEN :minPrice AND :maxPrice")
    List<Book> findBooksWithinPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

}