package com.unir.apirest_operaciones.service.customers;

import com.unir.apirest_operaciones.model.customers.customer.Customer;
import com.unir.apirest_operaciones.model.customers.requestcustomers.CreateCustomerRequest;

import java.util.List;

public interface ICustomersService {
    List<Customer> getCustomers();

    Customer getCustomer(String customerId);

    Boolean removeCustomer(String customerId);

    Customer createCustomer(CreateCustomerRequest request);
}
