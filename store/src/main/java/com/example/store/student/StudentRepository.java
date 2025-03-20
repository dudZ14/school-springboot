package com.example.store.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer>{ // JpaRepository<Classe,@Id>
    
    //findAllBy[nome do atributo da classe que se quer filtrar e tem de começar por Maiuscula][Containing || Like]
    List<Student> findAllByFirstNameContaining(String fname);

}