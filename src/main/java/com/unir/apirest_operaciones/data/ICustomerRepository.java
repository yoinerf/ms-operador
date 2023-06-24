package com.unir.apirest_operaciones.data;

import com.unir.apirest_operaciones.model.customers.customer.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;


public interface ICustomerRepository extends ElasticsearchRepository<Customer, String> {
    Optional<Customer> findById(String id);
    Customer save(Customer customer);
    void delete(Customer customer);

}
