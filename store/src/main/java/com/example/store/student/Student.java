package com.example.store.student;

import com.example.store.school.School;
import com.example.store.studentprofile.StudentProfile;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity // logo tem de ter um contrutor sem arugmentos
@Table(name="T_STUDENT") // se n usasse usaria student como o nome da tabela
public class Student {

    @Id // primary key
    @GeneratedValue // tabela gera id automaticamente, so funciona com Primary Key's, por default usa AUTO, que irá escolher SEQUENCE para postgresql, logo // os id's irao ser armazenados numa sequence (meio que tabela)
    private Integer id;

    @Column(name="c_fname", length = 20) //mudar o nome da coluna e o tamanho maximo do primeiro nome
    private String firstName;
    
    private String lastName;
    
    @Column(unique = true) //emails teem de ser diferentes
    private String email;

    private int age;

    @OneToOne(
        mappedBy = "student", // StudentProfile tem de ter atributo "student"
        cascade = CascadeType.ALL // se remover student, tambem removo o perfil
    )
    private StudentProfile studentProfile;

    @ManyToOne
    @JoinColumn( // FK
        name = "school_id" //nome da coluna 
    )
    @JsonBackReference // meter isto no filho para evitar loop (school tem students e student tem school)
    private School school;

    /* @Column(updatable = false) // por default = true, quando criamos um estudante nao quremos mudar a data de criacao
    private String creation_date; */

    public Student() {}; // pq tem @Entity 

    public Student(String firstName, String lastName, String email, int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public StudentProfile getStudentProfile(){
        return this.studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile){
        this.studentProfile = studentProfile;
    }

    public School getSchool(){
        return this.school;
    }

    public void setSchool(School school){
        this.school = school;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

}
