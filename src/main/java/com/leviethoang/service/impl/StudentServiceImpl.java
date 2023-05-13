package com.leviethoang.service.impl;

import com.leviethoang.dto.StudentDto;
import com.leviethoang.exception.StudentNotFoundException;
import com.leviethoang.model.Student;
import com.leviethoang.repository.StudentRepository;
import com.leviethoang.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;


    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream().map(
                student -> toStudentDto(student)).collect(Collectors.toList());
    }

    @Override
    public Student findById(Integer id) throws StudentNotFoundException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()){
            return optionalStudent.get();
        }
        else{
            throw new StudentNotFoundException("Can not find student with ID: " + id);
        }
    }

    @Override
    public Student update(Student student) {
        Student savedStudent = new Student();
        savedStudent.setId(student.getId());
        savedStudent.setFirstname(student.getFirstname());
        savedStudent.setLastname(student.getLastname());
        savedStudent.setAddress(student.getAddress());
        savedStudent.setEmail(student.getEmail());
        savedStudent.setDob(student.getDob());
        return studentRepository.save(savedStudent);
    }

    @Override
    public void deleteById(Integer id) throws StudentNotFoundException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()){
            studentRepository.deleteById(id);
        }
        else{
            throw new StudentNotFoundException("Can not find student with ID: " + id);
        }
    }

    public StudentDto toStudentDto(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setFirstname(student.getFirstname());
        studentDto.setLastname(student.getLastname());
        studentDto.setAddress(student.getAddress());
        return studentDto;
    }


}
