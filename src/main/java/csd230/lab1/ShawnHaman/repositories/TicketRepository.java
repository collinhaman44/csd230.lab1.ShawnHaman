package csd230.lab1.ShawnHaman.repositories;

import csd230.lab1.ShawnHaman.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}