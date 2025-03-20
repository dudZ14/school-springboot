package com.example.store.student;

//Exemplo de representecao de student (nao utiliza age, studentProfile)
public record StudentResponseDto(

    String firstName,
    String lastName,
    String email

) {
    
}