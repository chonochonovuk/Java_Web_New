package com.ecoverde.estateagency.repositories;

import com.ecoverde.estateagency.model.entity.Post;
import com.ecoverde.estateagency.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,String> {
    Optional<Post> findByAuthor(User author);
}
