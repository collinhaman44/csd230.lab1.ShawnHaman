package csd230.lab1.ShawnHaman.repositories;

import csd230.lab1.ShawnHaman.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}