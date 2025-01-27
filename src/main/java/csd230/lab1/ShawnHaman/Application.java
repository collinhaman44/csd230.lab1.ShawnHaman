package csd230.lab1.ShawnHaman;

import com.github.javafaker.Faker;
import csd230.lab1.ShawnHaman.entities.Book;
import csd230.lab1.ShawnHaman.entities.Cart;
import csd230.lab1.ShawnHaman.entities.Magazine;
import csd230.lab1.ShawnHaman.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

// YOU HAVE TO RUN THIS APPLICATION TWICE OR CART ITEM DOES NOT WORK PROPERLY
//DONT FORGET TO CHANGE THE PASSWORD BACK TO ITSTUDIES12345 AS WELL AS THE OTHER COMPOSE INFORMATION SO IT WORKS ON YOUR MACHINE

@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	CartItemRepository cartItemRepository;

	@Autowired
	CartRepository cartRepository;

	@Autowired MagazineRepository magazineRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			Cart cart=new Cart();
			cartRepository.save(cart);

			Faker faker = new Faker();
			com.github.javafaker.Book fakeBook = faker.book();
			com.github.javafaker.Number number = faker.number();
			com.github.javafaker.Code code=faker.code();

			// create some books user java faker
			for(int i=0;i<3;i++) {
				String title=fakeBook.title();
				double price=number.randomDouble(2, 10, 100);
				int copies=number.numberBetween(5, 20);
				int quantity=number.numberBetween(1, 50);
				String author=fakeBook.author();
				String isbn=code.isbn10();
				String description="Book: "+title;

				Book book = new Book(
						price,
						quantity,
						description,
						title,
						copies,
						author,
						isbn
				);

				cart.addItem(book);
				repository.save(book);
				cartRepository.save(cart);
			}

			// Create some magazines using Java Faker
			for(int i=0;i<3;i++) {
				String title=fakeBook.title();
				double price=number.randomDouble(2, 10, 100);
				int copies=number.numberBetween(5, 20);
				int quantity=number.numberBetween(1, 50);
				int orderQty=number.numberBetween(1, 50);
				Date currIssue = faker.date().birthday(); //birthday creates a random date so that you can fetch by a date
				String description = "Magazine: " + title;

				Magazine magazine = new Magazine(
						price,
						quantity,
						description,
						title,
						copies,
						orderQty,
						currIssue
				);

				cart.addItem(magazine);
				magazineRepository.save(magazine);
				cartRepository.save(cart);
			}

			// fetch all books
			log.info("BookEntitys found with findAll():");
			log.info("-------------------------------");
			repository.findAll().forEach(book -> {
				log.info(book.toString());
			});
			log.info("");

			// Fetch an individual book by ID
			Book book = repository.findById(1L);
			log.info("Book found with findById(1L):");
			log.info("--------------------------------");
			log.info(book.toString());
			log.info("");

			// fetch book by ISBN_10
			log.info("BookEntity found with findByIsbn('"+book.getIsbn()+"'):");
			log.info("--------------------------------------------");
			repository.findByIsbn( book.getIsbn()).forEach(isbn -> {
				log.info(isbn.toString());
			});
			log.info("");

			// Fetch books within a price range
			log.info("Books found within price range (10.0 - 50.0):");
			log.info("--------------------------------------------");
			List<Book> booksInPriceRange = repository.findBooksWithinPriceRange(10.0, 50.0);
			booksInPriceRange.forEach(b -> log.info(b.getTitle() + " - Price: " + b.getPrice())); //used getTitle so that it displays the titles of the books between the price range instead of just the book id
			log.info("");                                                                               //this also displays the prices of the books that are between the price range

			// Fetch books by title
			log.info("Books found with findByTitle('"+book.getTitle()+"'):");
			log.info("--------------------------------------------");
			repository.findByTitle( book.getTitle()).forEach(title -> {
				log.info(title.toString());
			});

			// Fetch all magazines
			log.info("Magazines found with findAll():");
			log.info("-------------------------------");
			magazineRepository.findAll().forEach(magazine ->
					log.info(magazine.toString()));
			log.info("");

			//Fetch an individual magazine by ID
			Magazine magazine = magazineRepository.findById(5L);
			log.info("Magazine found with findById(5L):");
			log.info("--------------------------------");
			log.info(magazine.toString());
			log.info("");

			//fetch a magazine by date (currIssue)
			log.info("magazines found with findByCurrIssue('"+magazine.getCurrIssue()+"'):");
			log.info("-------------------------------");
			magazineRepository.findByCurrIssue( magazine.getCurrIssue()).forEach(currIssue -> {
				log.info(currIssue.toString());
			} );

			//fetch a magazine by title
			log.info("Magazine found with findByTitle('"+magazine.getTitle()+"'):");
			log.info("-------------------------------");
			magazineRepository.findByTitle( magazine.getTitle()).forEach(title -> {
				log.info(title.toString());
			});

			// Fetch and log all items in the cart
			log.info("Cart items found with findAll():");
			log.info("--------------------------------------------");
			cartRepository.findAll().forEach(carT -> {
				log.info(carT.toString());
				cartItemRepository.findAll().forEach(cartItem -> {
					log.info("--------------------------------------------");
					log.info(cartItem.toString());
				});
			});
			log.info("");
		};
	}
}
