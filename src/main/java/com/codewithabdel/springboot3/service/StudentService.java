package com.codewithabdel.springboot3.service;

import com.codewithabdel.springboot3.model.Student;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    List<Student> studentList = new ArrayList<>();

    @Autowired
    private ObservationRegistry observationRegistry;

    public Student addStduent(Student student) {
        studentList.add(student);
       return Observation.createNotStarted("addStudent",observationRegistry)
               .observe(()->student);
    }

    public List<Student> getAllStudent() {
        return Observation.createNotStarted("getAllSTudent",observationRegistry)
                .observe(()->studentList);
    }

    public Student getStudent(String id) {
        Student student= studentList.stream()
                .filter(s-> s.id().equals(id))
                .findFirst()
                .orElseThrow(()->new RuntimeException("Student not found"));
        return  Observation.createNotStarted("getStudent", observationRegistry)
                .observe(()->student);
    }
}
