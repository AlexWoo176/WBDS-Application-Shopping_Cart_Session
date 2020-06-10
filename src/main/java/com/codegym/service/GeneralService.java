package com.codegym.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface GeneralService<E> {
    Page<E> findAll(Pageable pageable);
    Page<E> findAllByNameContaining(String name, Pageable pageable);
    E findById(Long id);
}
