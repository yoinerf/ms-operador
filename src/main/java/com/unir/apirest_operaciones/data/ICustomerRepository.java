package com.unir.apirest_operaciones.data;

import com.unir.apirest_operaciones.model.customers.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    //List<Customer> findByName(String names);
    //List<Customer> findAll();
   // Customer save(Customer customer);

//    Optional<ElasticIngredient> findByInternalName(String name);
//
//    List<ElasticIngredient> findAll();
//
//    ElasticIngredient save(ElasticIngredient ingredient);
}
