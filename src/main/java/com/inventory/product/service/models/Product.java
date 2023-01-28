package com.inventory.product.service.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.inventory.product.service.enums.Status;
import com.inventory.product.service.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    private UUID id = UUID.randomUUID();
    private String name;
    private String description;
    private Unit unit;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;
//    @ManyToMany(mappedBy = "products" , fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Set<Supplier> suppliers = new HashSet<>();
    private Status status;
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false, updatable = true)
    @UpdateTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
    @Column(name= "deleted_at", nullable = true)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime deletedAt;
}
