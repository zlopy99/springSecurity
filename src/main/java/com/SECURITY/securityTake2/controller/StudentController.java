package com.SECURITY.securityTake2.controller;

import com.SECURITY.securityTake2.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
