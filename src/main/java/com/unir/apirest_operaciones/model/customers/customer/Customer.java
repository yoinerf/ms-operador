package com.unir.apirest_operaciones.model.customers.customer;

import com.unir.apirest_operaciones.model.rentals.rental.Rental;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Primary;

import java.util.List;


@Entity
@Table(name = "clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="ID_CLIENTE", unique = true)
    private Integer ID_CLIENTE;

    @Column(name = "NOMBRES")
    private String NOMBRES;

    @Column(name = "APELLIDOS")
    private String APELLIDOS;

    @Column(name = "NUMERODOCUMENTO")
    private Integer NUMERODOCUMENTO;

    @OneToMany()
    @JoinTable(
            name = "alquiler",
            joinColumns = @JoinColumn(name = "ID_CLIENTE"),
            inverseJoinColumns = @JoinColumn(name = "ID_ALQUILER")
    )
    private List<Rental> Rentals;

}
