package com.rodrigoramos.desafiotecnico.api.repository;

import com.rodrigoramos.desafiotecnico.api.model.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesmanRepository extends JpaRepository<Salesman, Long> {

    Salesman findByName(String name);
}
