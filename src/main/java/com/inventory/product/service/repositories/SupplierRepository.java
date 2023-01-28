package com.inventory.product.service.repositories;


import com.inventory.product.service.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
}
