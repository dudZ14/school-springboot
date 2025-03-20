package com.example.store.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService){
        this.schoolService = schoolService;
    }

    @PostMapping("/schools")
    public SchoolDto create(@RequestBody SchoolDto dto){
        return schoolService.create(dto);
    }


    @GetMapping("/schools")
    public List<SchoolDto> findAll(){
        return this.schoolService.findAll();
    }

    
}
