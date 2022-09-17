package com.SECURITY.securityTake2.controller;

import com.SECURITY.securityTake2.model.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/api")
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "Pere"),
            new Student(2, "Mate"),
            new Student(3, "Ana"),
            new Student(4, "Marija")
    );

    @GetMapping("/students")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Student> getAllStudents(){
        return STUDENTS;
    }

    @PostMapping("/registerStudent")
//    @PreAuthorize("hasAuthority('course:write')")
    public void registerNewStudent(@RequestBody Student student){
        System.out.println(student);
    }

    @DeleteMapping("/deleteStudent/{id}")
//    @PreAuthorize("hasAuthority('course:write')")
    public void deleteStudent(@PathVariable("id") Integer id){
        System.out.println(id);
    }

    @PutMapping("/updateStudent/{id}")
//    @PreAuthorize("hasAuthority('course:write')")
    public void updateStudent(@PathVariable("id") Integer id, Student student){
        System.out.println(id + " " + student);
    }
}
