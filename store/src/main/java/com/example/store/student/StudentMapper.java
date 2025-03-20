package com.example.store.student;

import org.springframework.stereotype.Service;

import com.example.store.school.School;

@Service
public class StudentMapper {
    
    public Student toStudent(StudentDto dto){
        if (dto == null){
            throw new NullPointerException("The student Dto is null");
        }
        var student = new Student();
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());

        var school = new School();
        school.setId(dto.schoolId());

        student.setSchool(school);
        return student;

    }

    public StudentResponseDto toStudentResponseDto(Student student){
        return new StudentResponseDto(student.getFirstName(), student.getLastName(),student.getEmail());
    }
}
