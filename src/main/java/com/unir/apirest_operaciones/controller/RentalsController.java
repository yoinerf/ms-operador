package com.unir.apirest_operaciones.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import com.unir.apirest_operaciones.model.rentals.rental.Rental;
import com.unir.apirest_operaciones.model.rentals.requestrentals.CreateRentalRequest;
import com.unir.apirest_operaciones.service.rentals.IRentalsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RentalsController {
    private final IRentalsService service;


    @GetMapping("/rentals")
    public ResponseEntity<List<Rental>> getRentals(
            @RequestHeader Map<String, String> headers,
           @RequestParam(required = false) String client,
           @RequestParam(required = false) String movie){

        System.out.println(headers);
        List<Rental> rentals = service.getRentals(client, movie);

        return ResponseEntity.ok(Objects.requireNonNullElse(rentals, Collections.emptyList()));
    }

    @GetMapping("/rentals/{rentalId}")
    public ResponseEntity<Rental> getRental(@PathVariable String rentalId) {

        //log.info("Request received for product {}", productId);
        Rental rental = service.getRental(rentalId);

        if (rental != null) {
            return ResponseEntity.ok(rental);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/rentals/{rentalId}")
    public ResponseEntity<Void> deleteRental(@PathVariable String rentalId) {

        Boolean removed = service.removeRental(rentalId);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/rentals")
    public ResponseEntity<Rental> createRental(@RequestBody CreateRentalRequest request) {

        Rental createdRental = service.createRental(request);

        if (createdRental != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRental);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

}
