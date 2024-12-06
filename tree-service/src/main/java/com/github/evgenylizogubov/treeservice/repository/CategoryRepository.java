package com.github.evgenylizogubov.treeservice.repository;

import com.github.evgenylizogubov.treeservice.model.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getByName(String name);
    
    @Modifying
    @Transactional
    int deleteByName(String name);
}
