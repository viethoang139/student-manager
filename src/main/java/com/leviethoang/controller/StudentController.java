package com.leviethoang.controller;
import com.leviethoang.dto.StudentDto;
import com.leviethoang.exception.StudentNotFoundException;
import com.leviethoang.model.Student;
import com.leviethoang.service.impl.StudentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentServiceImpl service;

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody @Valid Student student){
        return ResponseEntity.ok(service.create(student));
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable("id") Integer id) throws StudentNotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping
    public ResponseEntity<Student> update(@RequestBody Student student){
        return ResponseEntity.ok(service.update(student));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id")Integer id) throws StudentNotFoundException {
        service.deleteById(id);
        return ResponseEntity.accepted().build();
    }

}
