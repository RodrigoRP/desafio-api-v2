package com.rodrigoramos.desafiotecnico.api.service;


import com.rodrigoramos.desafiotecnico.api.dto.CustomerNewDTO;
import com.rodrigoramos.desafiotecnico.api.model.Customer;
import com.rodrigoramos.desafiotecnico.api.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
@DirtiesContext
public class CustomerServiceTest {

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void shouldCreateCustomer() {
        // given
        Customer customer = new Customer(null, "85424488000137", "João", "Rural");

        // when
        Customer savedCustomer = customerService.save(customer);

        // then

        assertSoftly(softly -> {
            softly.assertThat(savedCustomer).isNotNull();
            softly.assertThat(savedCustomer.getBusinessArea()).isEqualTo(customer.getBusinessArea());
            softly.assertThat(savedCustomer.getName()).isEqualTo(customer.getName());
            softly.assertThat(savedCustomer.getId()).isNotNull();
            softly.assertThat(savedCustomer.getCnpj()).isEqualTo("85424488000137");
        });
    }

    @Test
    public void shouldDeleteCustomer() {
        // given
        Customer savedCustomer = new Customer(null, "85424488000137", "João", "Rural");
        customerService.save(savedCustomer);
        assertThat(customerRepository.findById(savedCustomer.getId())).isNotNull();

        // when
        customerService.delete(savedCustomer.getId());

        // then
        assertThat(customerRepository.findById(savedCustomer.getId())).isNotPresent();

    }


    @Test
    public void shouldFindById() {
        // given
        Customer savedCustomer = new Customer(null, "85424488000137", "João", "Rural");
        customerService.save(savedCustomer);

        // when
        Customer foundCustomer = customerService.find(savedCustomer.getId());

        // then
        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getId()).isEqualTo(savedCustomer.getId());

    }

    @Test
    public void shouldFindAll() {
        // given
        Customer savedCustomer1 = new Customer(null, "85424488000137", "João", "Rural");
        Customer savedCustomer2 = new Customer(null, "85424488000137", "Maria", "Rural");

        customerService.save(savedCustomer1);
        customerService.save(savedCustomer2);


        // when
        List<Customer> customers = customerService.findAll();

        // then
        assertThat(customers.stream().map(Customer::getId).collect(Collectors.toList()))
                .contains(savedCustomer1.getId(), savedCustomer2.getId());

    }

    @Test
    public void shouldReturnAllCustomers() {
        // given
        Customer user1 = new Customer(null, "85424488000137", "João", "Rural");
        Customer user2 = new Customer(null, "85424488000137", "Maria", "Rural");

        // when
        Long foundCustomer = customerService.getNumberOfCustomers();
        List<Customer> salesmen = customerService.findAll();

        // then
        assertThat(foundCustomer).isNotNull();
        assertThat(salesmen.size()).isEqualTo(foundCustomer.intValue());
    }


    @Test
    public void shouldConvertDtoToModel() {
        // given
        Customer expected = new Customer(null, "85424488000137", "João", "Rural");

        // when
        CustomerNewDTO dto = new CustomerNewDTO(expected.getCnpj(), expected.getName(), expected.getBusinessArea());

        // then
        assertThat(expected).isEqualToComparingFieldByField(customerService.convertToModel(dto));
    }


}
