package com.example.website_1.controller;

import com.example.website_1.dto.StudentDto;
import com.example.website_1.entity.Student;
import com.example.website_1.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @PostMapping("/students")
    public List<Student> addStudent(@RequestBody StudentDto studentDto) {
        Student student = Student.builder()
                .firstName(studentDto.name())
                .build();
        studentRepository.save(student);
        return studentRepository.findAll();
    }
}
