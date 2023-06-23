package com.unir.apirest_operaciones.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.unir.apirest_operaciones.service.customers.ICustomersService;
import com.unir.apirest_operaciones.model.customers.customer.Customer;
import com.unir.apirest_operaciones.model.customers.requestcustomers.CreateCustomerRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomersController {

    private final ICustomersService service;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCostumers (
            @RequestHeader Map<String, String> headers,
           @RequestParam(required = false) String nombre,
           @RequestParam(required = false) String apellido,
           @RequestParam(required = false) String documento) {

       System.out.println(headers);
       List<Customer> customers = service.getCustomers(nombre, apellido, documento);

        return ResponseEntity.ok(Objects.requireNonNullElse(customers, Collections.emptyList()));
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String customerId) {


        Customer customer = service.getCustomer(customerId);

        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String customerId) {

        Boolean removed = service.removeCustomer(customerId);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> getCustomer(@RequestBody CreateCustomerRequest request) {
        Customer createdCustomer = service.createCustomer(request);
        if (createdCustomer != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
