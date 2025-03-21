package com.example.website_1.controller;

import com.example.website_1.annotation.ValidatePermission;
import com.example.website_1.dto.StudentDto;
import com.example.website_1.entity.Student;
import com.example.website_1.repository.StudentRepository;
import com.example.website_1.service.StudentService;
import com.example.website_1.usercontext.UserContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@ValidatePermission({"MANAGE_USERS"})
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @GetMapping("/students")
    public List<Student> getStudents(HttpServletRequest request) {
        Assert.isTrue(!StringUtils.hasText(request.getHeader("Authorization")),
                "Authorization still exists");
        Assert.hasText(request.getHeader("x-user-id"),
                "Header does not have User Id");
        Assert.hasText(UserContextHolder.getUserContext().userId().toString(),
                "User Id does not exist in UserContext");
        return studentService.getAllStudents();
    }

    @GetMapping("/students-2")
    public List<Student> getStudents2(HttpServletRequest request) {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable(name = "studentId") UUID studentId) {
        return studentRepository.findById(studentId).get();
    }

    @ValidatePermission({"EDIT_PROFILE"})
    @PostMapping("/students")
    public List<Student> addStudent(@RequestBody StudentDto studentDto) {
        Student student = Student.builder()
                .firstName(studentDto.name())
                .build();
        studentRepository.save(student);
        return studentRepository.findAll();
    }
}
