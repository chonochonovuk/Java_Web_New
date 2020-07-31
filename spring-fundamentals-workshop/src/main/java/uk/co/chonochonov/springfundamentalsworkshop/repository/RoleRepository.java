package uk.co.chonochonov.springfundamentalsworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.co.chonochonov.springfundamentalsworkshop.model.entity.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findByName(String name);
}
