package com.unir.apirest_operaciones.model.movies;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieGenere {

    private Movie movie;


    private Genere genero;



}
