package com.rodrigoramos.desafiotecnico.api.repository;

import com.rodrigoramos.desafiotecnico.api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
