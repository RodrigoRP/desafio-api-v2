package com.rodrigoramos.desafiotecnico.api.service;

import com.rodrigoramos.desafiotecnico.api.dto.CustomerNewDTO;
import com.rodrigoramos.desafiotecnico.api.model.Customer;
import com.rodrigoramos.desafiotecnico.api.repository.CustomerRepository;
import com.rodrigoramos.desafiotecnico.api.service.exceptions.DataIntegrityException;
import com.rodrigoramos.desafiotecnico.api.service.exceptions.ObjectNotFoundException;
import com.rodrigoramos.desafiotecnico.api.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer find(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found!"));
    }

    @Override
    public void delete(Long id) {
        find(id);
        try {
            customerRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Unable to delete!");
        }
    }

    public Customer convertToModel(CustomerNewDTO customerNewDTO) {
        return new Customer(null, customerNewDTO.getCnpj(), customerNewDTO.getName(), customerNewDTO.getBusinessArea());
    }

    public Long getNumberOfCustomers() {
        return customerRepository.count();
    }
}
