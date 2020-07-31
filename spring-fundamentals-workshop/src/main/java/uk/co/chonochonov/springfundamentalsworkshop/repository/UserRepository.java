package uk.co.chonochonov.springfundamentalsworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.co.chonochonov.springfundamentalsworkshop.model.entity.User;
import uk.co.chonochonov.springfundamentalsworkshop.model.service.UserServiceModel;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsername(String username);
}






