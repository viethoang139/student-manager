package com.leviethoang.service;

import com.leviethoang.exception.StudentNotFoundException;
import com.leviethoang.model.Student;
import com.leviethoang.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository repository;

    @BeforeEach
    void setUp() {
        Student student =
                Student.builder()
                        .id(1)
                        .firstname("Le Viet")
                        .lastname("Hoang")
                        .address("Tuyen Quang")
                        .email("levieth710@gmail.com")
                        .dob("13/09/2002").build();

        Mockito.when(repository.findById(1))
                .thenReturn(Optional.ofNullable(student));

    }



}