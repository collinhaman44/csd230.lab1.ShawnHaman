package csd230.lab1.ShawnHaman.repositories;

import csd230.lab1.ShawnHaman.entities.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {

    List<Magazine> findByCurrIssue(Date currIssue);

    Magazine findById(long id);

    List<Magazine> findByTitle(String title);
}