package com.leviethoang.service;

import com.leviethoang.dto.StudentRequest;
import com.leviethoang.dto.StudentResponse;
import com.leviethoang.exception.StudentNotFoundException;
import com.leviethoang.model.Student;

import java.util.List;

public interface StudentService {
    public Student create(StudentRequest studentRequest);
    public List<StudentResponse> findAll();
    public StudentResponse findById(Integer id) throws StudentNotFoundException;
    public StudentResponse update(Student student ,Integer id) throws StudentNotFoundException;
    public void deleteById(Integer id) throws StudentNotFoundException;
}
