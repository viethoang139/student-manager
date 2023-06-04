package com.leviethoang.controller;
import com.leviethoang.dto.StudentRequest;
import com.leviethoang.dto.StudentResponse;
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
    public ResponseEntity<Student> create(@RequestBody @Valid StudentRequest studentRequest){
        return ResponseEntity.ok(service.create(studentRequest));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> findById(@PathVariable("id") Integer id) throws StudentNotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> update(@RequestBody Student student, @PathVariable("id") Integer studentId) throws StudentNotFoundException {
        return ResponseEntity.ok(service.update(student,studentId));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id")Integer id) throws StudentNotFoundException {
        service.deleteById(id);
        return ResponseEntity.accepted().build();
    }

}
