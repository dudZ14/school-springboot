package com.example.store.school;

import java.util.List;

import com.example.store.student.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class School {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany( // como usa mappedBy n cria coluna nesta entidade
        mappedBy = "school" // nao adiciona coluna 
    )
    @JsonManagedReference // meter isto no pai para evitar loop (school tem students e student tem school)
    private List<Student> students;

    public School() {}

    public School(String name){
        this.name = name;
    }

    public Integer getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public List<Student> getStudents(){
        return students;
    }

    public void setStudents(List<Student> students){
        this.students = students;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setName(String name){
        this.name= name;
    }

}
