package csd230.lab1.ShawnHaman.repositories;

import csd230.lab1.ShawnHaman.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
}