package com.leviethoang.service.impl;

import com.leviethoang.dto.StudentRequest;
import com.leviethoang.dto.StudentResponse;
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
    public Student create(StudentRequest studentRequest) {
        Student student = Student.builder()
                .firstname(studentRequest.getFirstname())
                .lastname(studentRequest.getLastname())
                .address(studentRequest.getAddress())
                .email(studentRequest.getEmail())
                .dob(studentRequest.getDob()).build();
        return studentRepository.save(student);
    }

    @Override
    public List<StudentResponse> findAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponse> studentResponseList = students.stream().map(this::mapToStudentResponse)
                .collect(Collectors.toList());
        return studentResponseList;
    }

    private StudentResponse mapToStudentResponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setFirstname(student.getFirstname());
        studentResponse.setLastname(student.getLastname());
        studentResponse.setAddress(student.getAddress());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setDob(student.getDob());
        return studentResponse;
    }

    @Override
    public StudentResponse findById(Integer id) throws StudentNotFoundException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()){
            Student student = optionalStudent.get();
            return mapToStudentResponse(student);
        }
        else{
            throw new StudentNotFoundException("Can not found student with ID: " + id);
        }
    }

    @Override
    public StudentResponse update(Student student,Integer id) throws StudentNotFoundException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()){
            StudentResponse studentResponse = new StudentResponse();
            studentResponse.setFirstname(student.getFirstname());
            studentResponse.setLastname(student.getLastname());
            studentResponse.setAddress(student.getAddress());
            studentResponse.setEmail(student.getEmail());
            studentResponse.setDob(student.getDob());
            return mapToStudentResponse(student);
        }
        else{
            throw new StudentNotFoundException("Can not found student with ID: " + id);
        }
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

}
