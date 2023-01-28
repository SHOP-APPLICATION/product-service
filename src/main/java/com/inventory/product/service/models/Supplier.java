package com.inventory.product.service.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inventory.product.service.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

import java.util.UUID;

@Entity(name = "suppliers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Supplier {
    @Id
    private UUID id = UUID.randomUUID();
    private String name;
//    @ManyToMany( fetch = FetchType.EAGER)
//    @JoinTable(name="suppliers_products",
//            joinColumns = {@JoinColumn(name="supplier_id")},
//            inverseJoinColumns = {@JoinColumn(name="product_id")})
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private Set<Product> products = new HashSet<>();
    private Status status;
    @Column(nullable = true)
    private String raison;
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false, updatable = true)
    @UpdateTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at", nullable = true)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime deletedAt;
}
