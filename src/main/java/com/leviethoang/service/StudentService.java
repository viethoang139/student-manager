package com.leviethoang.service;

import com.leviethoang.dto.StudentDto;
import com.leviethoang.exception.StudentNotFoundException;
import com.leviethoang.model.Student;

import java.util.List;

public interface StudentService {
    public Student create(Student student);
    public List<StudentDto> findAll();
    public Student findById(Integer id) throws StudentNotFoundException;
    public Student update(Student student);
    public void deleteById(Integer id) throws StudentNotFoundException;
}
