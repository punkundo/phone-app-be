package com.thuthao.btl1_react.service;

import com.thuthao.btl1_react.entities.StudentEntity;
import com.thuthao.btl1_react.model.Student;
import com.thuthao.btl1_react.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");

    public Student createStudent(Student student) throws ParseException {
        StudentEntity studentEntity = new StudentEntity(student.getId(), student.getName(), student.getPrice(),
                student.getBrand(), student.getSold());
        studentRepository.save(studentEntity);
        return student;
    }

    public List<Student> getListStudents() {
        List<StudentEntity> students = studentRepository.findAll();
        List<Student> students1 = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            Student student2 = new Student(students.get(i).getId(), students.get(i).getName(),
                    students.get(i).getPrice(), students.get(i).getBrand(), students.get(i).getSold());
            students1.add(student2);
        }
        return students1;
    }

    public boolean deleteStudent(String id) {
        StudentEntity student = studentRepository.findById(id).get();
        studentRepository.delete(student);
        return true;
    }

    public Student getStudent(String id) {
        StudentEntity studentEntity = null;
        Student student = null;
        try {
            studentEntity = studentRepository.findById(id).get();
            System.out.println(studentEntity.getId());
            System.out.println(studentEntity.getPrice());
            // student = new Student(studentEntity.getId(), studentEntity.getName(),
            // studentEntity.getPrice(), student)
            student = new Student(studentEntity.getId(), studentEntity.getName(), studentEntity.getPrice(),
                    studentEntity.getBrand(), studentEntity.getSold());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (studentEntity == null) {
            return null;
        }
        return student;
    }

    public Student editStudent(Student student) throws ParseException {
        StudentEntity stu = new StudentEntity(student.getId(), student.getName(), student.getPrice(),
                student.getBrand(), student.getSold());
        // BeanUtils.copyProperties(student,stu);

        StudentEntity student1 = studentRepository.save(stu);
        Student stu1 = new Student();
        BeanUtils.copyProperties(student1, stu1);
        return stu1;
    }
}
