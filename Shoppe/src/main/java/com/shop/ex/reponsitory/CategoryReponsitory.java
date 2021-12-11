package com.shop.ex.reponsitory;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.ex.domain.Category;

@Repository
public interface CategoryReponsitory extends JpaRepository<Category, Long> {
       List<Category> findByNameContaining(String name);
       Page<Category> findByNameContaining(String name,Pageable pageable);      
}
