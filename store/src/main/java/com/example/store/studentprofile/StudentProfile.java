package com.example.store.studentprofile;

import com.example.store.student.Student;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class StudentProfile {

    @Id 
    @GeneratedValue
    private Integer id;
    
    private String bio;

    @OneToOne
    @JoinColumn(
        name = "student_id" //logo studentprofile tera coluna a mais chamada student_id (FK)
    )
    private Student student; // tem de ser = ao mappedBy na classe Student
    
    public StudentProfile() {}

    public StudentProfile(String bio){
        this.bio = bio;
    }

    public Integer getId(){
        return this.id;
    }

    public String getBio(){
        return this.bio;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setName(String bio){
        this.bio = bio;
    }




    
}
