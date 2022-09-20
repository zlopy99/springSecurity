package com.SECURITY.securityTake2.controller;

import com.SECURITY.securityTake2.JWT.JwtUsernamePasswordAuthFilter;
import com.SECURITY.securityTake2.model.Student;
import com.SECURITY.securityTake2.model.UsernameAndPasswordAuthRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private static final List<Student>  STUDENTS = Arrays.asList(
            new Student(1, "Pere"),
            new Student(2, "Mate"),
            new Student(3, "Ana"),
            new Student(4, "Marija")
    );

    @GetMapping("/student/{Id}")
    public Student getStudent(@PathVariable("Id") Integer studentId){
        return STUDENTS.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + studentId + " does not exists"));
    }

//    @PostMapping("/login")
//    public void login(@RequestBody UsernameAndPasswordAuthRequest authRequest){
//
//    }
}
