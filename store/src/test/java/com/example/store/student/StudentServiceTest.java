package com.example.store.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class StudentServiceTest {
    
    // o que queremos testar
    @InjectMocks // injetar as dependencias
    private StudentService studentService;

    //dependencias que seram simuladas, de forma a testar studentService de forma isolada
    @Mock
    StudentRepository studentRepository;
    @Mock
    StudentMapper studentMapper;



    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this); //abrir os mocks para esta classe
    }

    @Test
    public void should_save_student(){
        //given 
        StudentDto dto = new StudentDto("John","Does", "john@mail.com", 1);

        Student student = new Student("John","Does", "john@mail.com",20);

        Student savedStudent = new Student("John","Does", "john@mail.com",20);
        savedStudent.setId(1);

        //mock the calls which use the mock services 
        Mockito.when(studentMapper.toStudent(dto)).thenReturn(student);
        Mockito.when(studentRepository.save(student)).thenReturn(savedStudent);
        Mockito.when(studentMapper.toStudentResponseDto(savedStudent)).thenReturn(new StudentResponseDto("John", "Does", "john@mail.com"));

        //when
        StudentResponseDto resDto = studentService.saveStudent(dto);

        //then
        assertEquals(dto.firstName(), resDto.firstName());
        assertEquals(dto.lastName(), resDto.lastName());
        assertEquals(dto.email(), resDto.email());

        //make sure that the mocked functions are called 1 time
        Mockito.verify(studentMapper,Mockito.times(1)).toStudent(dto);
        Mockito.verify(studentRepository,Mockito.times(1)).save(student);
        Mockito.verify(studentMapper,Mockito.times(1)).toStudentResponseDto(savedStudent);

    }

    @Test 
    public void find_all_test(){
        //given
        List<Student> students = new ArrayList<>();
        students.add(new Student("John","Does", "john@mail.com",20));

        //mock the calls
        Mockito.when(studentRepository.findAll()).thenReturn(students);
        Mockito.when(studentMapper.toStudentResponseDto((any(Student.class)))).thenReturn(new StudentResponseDto("John", "Does", "john@mail.com"));

        //when
        List<StudentResponseDto> resDto = studentService.findAllStudents();

        //then
        assertEquals(students.size(), resDto.size());

        Mockito.verify(studentRepository,times(1)).findAll();

    }

    @Test 
    public void find_student_by_id_test(){
        //given
        int studentId = 1;
        Student student = new Student("John","Does", "john@mail.com",20);

        //mock calls
        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        Mockito.when(studentMapper.toStudentResponseDto((any(Student.class)))).thenReturn(new StudentResponseDto("John", "Does", "john@mail.com"));

        //when
        Optional<StudentResponseDto> predicted = studentService.findStudentById(studentId);

        //then
        assertEquals(predicted.get().firstName(), student.getFirstName());
        assertEquals(predicted.get().lastName(), student.getLastName());
        assertEquals(predicted.get().email(), student.getEmail());

        Mockito.verify(studentRepository,times(1)).findById(studentId);
    }

    @Test 
    public void find_student_by_name_test(){
        //given
        String name = "John";
        List<Student> students = new ArrayList<>();
        students.add(new Student("John","Does", "john@mail.com",20));

        //mock calls
        Mockito.when(studentRepository.findAllByFirstNameContaining(name)).thenReturn((students));
        Mockito.when(studentMapper.toStudentResponseDto((any(Student.class)))).thenReturn(new StudentResponseDto("John", "Does", "john@mail.com"));

        //when
        List<StudentResponseDto> predicted = studentService.findStudentByName(name);

        //then
        assertEquals(students.size(), predicted.size());

        Mockito.verify(studentRepository,times(1)).findAllByFirstNameContaining(name);
    }

    // mockito.when nao eh aplicavel para funcoes do tipo void, logo n da para testar delete


}
