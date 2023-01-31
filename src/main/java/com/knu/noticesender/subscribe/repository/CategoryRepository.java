package com.knu.noticesender.subscribe.repository;

import com.knu.noticesender.subscribe.model.Category;
import com.knu.noticesender.subscribe.model.Category.CategoryId;
import com.knu.noticesender.subscribe.model.SubscribeType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, CategoryId> {
    List<Category> findCategoriesById_SubscribeId(String subId);
    List<Category> findCategoriesById_Type(SubscribeType type);
}
