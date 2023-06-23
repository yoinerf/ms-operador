package com.unir.apirest_operaciones.service.customers;

import com.unir.apirest_operaciones.data.ICustomerRepository;
import com.unir.apirest_operaciones.model.customers.customer.Customer;
import com.unir.apirest_operaciones.model.customers.requestcustomers.CreateCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CustomersService implements ICustomersService {

    @Autowired
    private ICustomerRepository repository;

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = repository.findAll();
        System.out.println(customers);
        return customers.isEmpty() ? null : customers;
    }

    @Override
    public Customer getCustomer(String customerId) {

        return repository.findById(Integer.valueOf(customerId)).orElse(null);
    }

    @Override
    public Boolean removeCustomer(String customerId) {

        Customer customer = repository.findById(Integer.valueOf(customerId)).orElse(null);

        if (customer != null) {
            repository.delete(customer);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Customer createCustomer(CreateCustomerRequest request) {

        if (request != null && StringUtils.hasLength(request.getNOMBRES().trim())
                && StringUtils.hasLength(request.getAPELLIDOS().trim())
                && request.getNUMERODOCUMENTO() != null) {

            Customer customer = Customer.builder().NOMBRES(request.getNOMBRES()).APELLIDOS(request.getAPELLIDOS()).NUMERODOCUMENTO(request.getNUMERODOCUMENTO()).Rentals(request.getRENTALS())
                    .build();

            return repository.save(customer);
        } else {
            return null;
        }
    }
}
