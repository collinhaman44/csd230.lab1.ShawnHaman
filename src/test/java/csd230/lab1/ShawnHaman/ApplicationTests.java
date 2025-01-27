package csd230.lab1.ShawnHaman;

import com.github.javafaker.Faker;
import csd230.lab1.ShawnHaman.entities.Book;
import csd230.lab1.ShawnHaman.entities.Cart;
import csd230.lab1.ShawnHaman.repositories.BookRepository;
import csd230.lab1.ShawnHaman.repositories.CartItemRepository;
import csd230.lab1.ShawnHaman.repositories.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // use application db (mysql) not default h2 embedded db
@Transactional(propagation = Propagation.NOT_SUPPORTED) // don't rollback so you can see data in the db
public class ApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(ApplicationTests.class);

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Test
	@Transactional
	void testSaveAndFetchBookInCart() {
		Faker faker = new Faker();

		// Create a Cart
		Cart cart = new Cart();
		cartRepository.save(cart);

		// Generate fake Book data
		String author = faker.book().author();
		String isbn = faker.code().isbn10();
		String title = faker.book().title();
		double price = faker.number().randomDouble(2, 10, 100);
		int quantity = faker.number().numberBetween(1, 10);

		// Create and save a Book entity
		Book book = new Book();
		book.setAuthor(author);
		book.setISBN(isbn);
		book.setTitle(title);
		book.setPrice(price);
		book.setQuantity(quantity);
		book.setCart(cart);

		cart.addItem(book);
		bookRepository.save(book);
		cartRepository.save(cart);

		// Retrieve books using queries in BookRepository
		List<Book> booksByISBN = bookRepository.findByISBN(isbn);
		Book bookById = bookRepository.findById(book.getId()).orElseThrow();
		List<Book> booksInPriceRange = bookRepository.findBooksWithinPriceRange(5.0, 50.0);

		// Log the retrieved books
		log.info("Books retrieved by ISBN:");
		booksByISBN.forEach(b -> log.info(b.toString()));

		log.info("Book retrieved by ID:");
		log.info(bookById.toString());

		log.info("Books retrieved within price range:");
		booksInPriceRange.forEach(b -> log.info(b.toString()));

		// Verify the Cart contains the Book
		Cart fetchedCart = cartRepository.findById(cart.getId()).orElseThrow();
		assertEquals(1, fetchedCart.getItems().size());
		assertEquals(book.getId(), fetchedCart.getItems().iterator().next().getId());

		// Fetch and log all items in the cart
		log.info("CartItemEntitys found with findAll():");
		log.info("-------------------------------");
		cartRepository.findAll().forEach(carT -> {
			log.info(carT.toString());
			cartItemRepository.findAll().forEach(cartItem -> {
				log.info("-------------------------------");
				log.info(cartItem.toString());
			});
		});
		log.info("");
	}
}
