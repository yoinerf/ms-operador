package com.unir.apirest_operaciones.model.rentals.rental;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;


@Document(indexName = "rentals", createIndex = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Rental {

    @Id
    private String id;

    @MultiField(mainField = @Field(type = FieldType.Keyword, name = "client"),otherFields = @InnerField(
            suffix = "search", type=  FieldType.Search_As_You_Type))
    private String client;

    @MultiField(mainField = @Field(type = FieldType.Keyword, name = "movie"),otherFields = @InnerField(
            suffix = "search", type=  FieldType.Search_As_You_Type))
    private String movie;

    @Field(type = FieldType.Keyword, name = "FECHA_INICIO_ALQUILER")
    private String FECHA_INICIO_ALQUILER;

    @Field(type = FieldType.Keyword, name = "FECHA_FIN_ALQUILER")
    private String FECHA_FIN_ALQUILER;


}
