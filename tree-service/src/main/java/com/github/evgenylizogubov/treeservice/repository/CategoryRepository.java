package com.github.evgenylizogubov.treeservice.repository;

import com.github.evgenylizogubov.treeservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getByName(String name);
}
