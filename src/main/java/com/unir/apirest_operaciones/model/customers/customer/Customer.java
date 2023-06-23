package com.unir.apirest_operaciones.model.customers.customer;

import com.unir.apirest_operaciones.model.rentals.rental.Rental;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.List;


@Document(indexName = "costumers", createIndex = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Customer {

    @Id
    private String id;

    @MultiField(mainField = @Field(type = FieldType.Keyword, name = "nombres"),otherFields = @InnerField(
            suffix = "search", type=  FieldType.Search_As_You_Type))
    private String nombres;

    @MultiField(mainField = @Field(type = FieldType.Keyword, name = "apellidos"),otherFields = @InnerField(
            suffix = "search", type=  FieldType.Search_As_You_Type))
    private String apellidos;

    @Field(type = FieldType.Integer, name = "documento")
    private Integer documento;


    @Field(type = FieldType.Nested, name = "Rentals")
    private List<Rental> Rentals;

}
