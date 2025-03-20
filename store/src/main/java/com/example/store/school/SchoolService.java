package com.example.store.school;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {
    
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper){
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public SchoolDto create(SchoolDto dto){
        var school = this.schoolMapper.toSchool(dto);
        this.schoolRepository.save(school);
        return dto;
    }

    public List<SchoolDto> findAll(){
        return this.schoolRepository.findAll().stream().map(this.schoolMapper::toSchoolDto).collect(Collectors.toList());
    }
}
