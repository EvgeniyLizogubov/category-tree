package com.github.evgenylizogubov.web.repository;

import com.github.evgenylizogubov.web.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
