package com.examprep.springbasicexamprep.repository;

import com.examprep.springbasicexamprep.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,String> {
}
