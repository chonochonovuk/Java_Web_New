package uk.co.chonochonov.springfundamentalsworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.co.chonochonov.springfundamentalsworkshop.model.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,String> {
}
