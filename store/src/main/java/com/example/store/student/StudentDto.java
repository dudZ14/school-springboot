package com.example.store.student;

import jakarta.validation.constraints.NotBlank;

//Exemplo de representecao de student (nao utiliza age, studentProfile)
//Fazer validacao aqui, pq é isto que eh enviado no corpo 
public record StudentDto(

    @NotBlank(message = "Firstname should not be empty!") //example of data validation
    String firstName,
    
    @NotBlank(message = "Lastname should not be empty!")
    String lastName,

    String email,

    Integer schoolId //FK

) {
    
}
