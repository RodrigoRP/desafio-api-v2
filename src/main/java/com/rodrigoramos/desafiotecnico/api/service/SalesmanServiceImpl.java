package com.rodrigoramos.desafiotecnico.api.service;

import com.rodrigoramos.desafiotecnico.api.model.Salesman;
import com.rodrigoramos.desafiotecnico.api.repository.SalesmanRepository;
import com.rodrigoramos.desafiotecnico.api.service.exceptions.ObjectNotFoundException;
import com.rodrigoramos.desafiotecnico.api.service.interfaces.SalesmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesmanServiceImpl implements SalesmanService {

    private final SalesmanRepository salesmanRepository;

    @Autowired
    public SalesmanServiceImpl(SalesmanRepository salesmanRepository) {
        this.salesmanRepository = salesmanRepository;
    }

    @Override
    public Salesman save(Salesman salesman) {
        Salesman name = findByName(salesman.getName());
        if (name == null) {
            return salesmanRepository.save(salesman);
        }
        return name;
    }

    @Override
    public List<Salesman> findAll() {
        return salesmanRepository.findAll();
    }

    @Override
    public long getNumberOfSalespeople() {
        return salesmanRepository.count();
    }

    @Override
    public Salesman findByName(String name) {
        return salesmanRepository.findByName(name);
    }

    @Override
    public Salesman find(Long id) {
        Optional<Salesman> salesman = salesmanRepository.findById(id);
        return salesman.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found!"));
    }

    @Override
    public void delete(Long id) {
        find(id);
        salesmanRepository.deleteById(id);

    }

}

