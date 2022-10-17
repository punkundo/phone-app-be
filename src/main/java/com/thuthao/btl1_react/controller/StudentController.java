package com.thuthao.btl1_react.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thuthao.btl1_react.model.Student;
import com.thuthao.btl1_react.service.StudentService;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/createStudent")
    public ResponseEntity<Map<String, String>> createStudent(@RequestBody Student student) throws ParseException {
        Map<String, String> map = new HashMap<>();
        if (studentService.getStudent(student.getId()) == null) {
            studentService.createStudent(student);
            map.put("title", "Đã thực hiện thành công");
            map.put("status", "true");
        } else {
            map.put("title", "ID đã bị trùng.");
            map.put("status", "false");
        }
        return ResponseEntity.ok(map);
    }

    @GetMapping("/")
    public List<Student> getListStudent() {
        return studentService.getListStudents();
    }

    @PostMapping("/deleted/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable("id") String id) {
        boolean deleted = false;
        deleted = studentService.deleteStudent(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("status", deleted);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/student/{id}")
    public Student getStudent(@PathVariable("id") String id) {
        return studentService.getStudent(id);
    }

    @PostMapping("/edit")
    public Student editStudent(@RequestBody Student student) throws ParseException {
        return studentService.editStudent(student);
    }
}
