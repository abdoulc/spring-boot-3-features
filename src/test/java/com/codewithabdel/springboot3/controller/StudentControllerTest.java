package com.codewithabdel.springboot3.controller;

import com.codewithabdel.springboot3.model.Student;
import com.codewithabdel.springboot3.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void add() throws Exception {

        Student student = buildStudent();
        String studentAsString = objectMapper.writeValueAsString(student);
        Mockito.when(studentService.addStduent(any())).thenReturn(student);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/addStduent")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(studentAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultS = result.getResponse().getContentAsString();
        Student student1 = objectMapper.readValue(resultS, Student.class);

        Assertions.assertNotNull(student1);
        Assertions.assertEquals("test", student1.name());

    }

    @Test
    void getAll() {
    }

    @Test
    void getStudent() {
    }

    private Student buildStudent(){
        return new Student("1","test","test@email.com");
    }
}