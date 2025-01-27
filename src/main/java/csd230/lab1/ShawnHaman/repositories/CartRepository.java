package csd230.lab1.ShawnHaman.repositories;

import csd230.lab1.ShawnHaman.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
}