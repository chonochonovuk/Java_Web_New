package uk.co.chonochonov.springfundamentalsworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.co.chonochonov.springfundamentalsworkshop.model.entity.Homework;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework,String> {
}
