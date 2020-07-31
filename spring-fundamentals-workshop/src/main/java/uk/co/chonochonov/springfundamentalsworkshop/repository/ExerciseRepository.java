package uk.co.chonochonov.springfundamentalsworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.co.chonochonov.springfundamentalsworkshop.model.entity.Exercise;

import java.util.Optional;


@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, String> {
    Optional<Exercise> findByName(String name);
}
