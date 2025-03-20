package com.example.store.student;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper; // serviço auxiliar

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper ){
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }
    
    public StudentResponseDto saveStudent(StudentDto dto){
        var student = studentMapper.toStudent(dto);
        var savedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> findAllStudents(){  
        return studentRepository.findAll().stream().map(this.studentMapper::toStudentResponseDto).collect(Collectors.toList());
    }

    public Optional<StudentResponseDto> findStudentById(Integer id){  
        return studentRepository.findById(id).map(this.studentMapper::toStudentResponseDto); // se n existir, = null
    }

    public List<StudentResponseDto> findStudentByName(String name){  
        return studentRepository.findAllByFirstNameContaining(name).stream().map(this.studentMapper::toStudentResponseDto).collect(Collectors.toList());
         // se n existir, = null
    }

    public void delete(Integer id){  
        studentRepository.deleteById(id); // se n existir, = null
    }
}
