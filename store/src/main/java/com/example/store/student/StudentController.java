package com.example.store.student;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired // D.I
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    // NECESSARIO @RequestBody , pq é ele o responsavel por mappear o body do postman para a variavel "message" 
    @PostMapping("/students")
    public StudentResponseDto saveStudent(@Valid @RequestBody StudentDto dto){ //@Valid, significa que iremos fazer data validation (que sera tratada no StudentDto.java)
        return studentService.saveStudent(dto);
    }

    @GetMapping("/students")
    public List<StudentResponseDto> findAllStudents(){  
        return studentService.findAllStudents();
    }
    
    @GetMapping("/students/{student-id}")
    public Optional<StudentResponseDto> findStudentById(@PathVariable("student-id") Integer id){  
        return studentService.findStudentById(id);
    }

    @GetMapping("/students/search/{student-name}")
    public List<StudentResponseDto> findStudentByName(@PathVariable("student-name") String name){  
        return studentService.findStudentByName(name); 
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("student-id") Integer id){  
        studentService.delete(id); 
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // sempre que a aplicacao der throw de MethodArgumentNotValidException (pq n meteu ultimo nome no body, por ex)
                                                            // esta funcao sera executada
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        var errors = new HashMap<String,String>();
        
        e.getBindingResult().getAllErrors().forEach(error -> { var fieldName = ((FieldError) error).getField();
                                                               var errorMsg = error.getDefaultMessage();
                                                               errors.put(fieldName,errorMsg);   }); 

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);                                     
    }
}
