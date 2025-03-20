package com.example.store.student;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;   

public class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp(){
        mapper = new StudentMapper();
    }
    
    @Test
    public void shouldMapStudentDtoToStudentClass(){
        //given
        StudentDto dto = new StudentDto("John","Does", "john@mail.com", 1);

        //when
        Student student = mapper.toStudent(dto);

        //then
        assertEquals(dto.firstName(), student.getFirstName());
        assertEquals(dto.lastName(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool()); // para n ter NullPointerException na linha seguinte
        assertEquals(dto.schoolId(), student.getSchool().getId());

        //testar null
        var e = assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        assertEquals("The student Dto is null", e.getMessage());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto(){
        //given
        Student student = new Student("John","Doe","john@mail.com",40);

        //when
        StudentResponseDto studentResponseDto = mapper.toStudentResponseDto(student);

        //then
        assertEquals(studentResponseDto.firstName(), student.getFirstName());
        assertEquals(studentResponseDto.lastName(), student.getLastName());
        assertEquals(studentResponseDto.email(), student.getEmail());
    }
    
}
