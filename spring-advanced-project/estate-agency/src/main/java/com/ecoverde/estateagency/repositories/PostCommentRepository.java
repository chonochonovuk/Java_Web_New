package com.ecoverde.estateagency.repositories;

import com.ecoverde.estateagency.model.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment,String> {
}
