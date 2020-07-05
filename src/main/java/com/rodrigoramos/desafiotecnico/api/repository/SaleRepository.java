package com.rodrigoramos.desafiotecnico.api.repository;

import com.rodrigoramos.desafiotecnico.api.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository  extends JpaRepository<Sale, Long> {

    List<Sale> findAll();
}
