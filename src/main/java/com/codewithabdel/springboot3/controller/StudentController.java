package com.codewithabdel.springboot3.controller;

import com.codewithabdel.springboot3.model.Student;
import com.codewithabdel.springboot3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/addStduent")
    public Student addStduent(@RequestBody Student student){
        return studentService.addStduent(student);
    }

    @GetMapping("/all")
    public List<Student> getAll(){
        return studentService.getAllStudent();
    }

    @GetMapping("/{id}")
    public  Student getStudent(@PathVariable("id") String id){
        return studentService.getStudent(id);
    }
}
