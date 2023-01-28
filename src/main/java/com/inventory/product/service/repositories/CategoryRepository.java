package com.inventory.product.service.repositories;


import com.inventory.product.service.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository  extends JpaRepository<Category, UUID> {
}
