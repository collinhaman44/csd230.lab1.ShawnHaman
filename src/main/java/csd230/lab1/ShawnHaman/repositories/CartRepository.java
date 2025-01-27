package csd230.lab1.ShawnHaman.repositories;

import csd230.lab1.ShawnHaman.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}