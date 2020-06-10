package com.codegym.repositories;

import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryPagingAndSorting extends PagingAndSortingRepository<Product,Long> {
    Page<Product> findAllByNameContaining(String name, Pageable pageable);
}
