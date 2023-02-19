package ru.hogwarts.school.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.services.StudentService;

import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    public StudentController(StudentService studentService,
                             StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public ResponseEntity findStudents(@RequestParam(required = false) Long id,
                                       @RequestParam(required = false) Integer age1,
                                       @RequestParam(required = false) Integer age2) {
        if (id != null) {
            return ResponseEntity.ok(studentService.findById(id));
        }
        if (age1 != null && age2 != null) {
            return ResponseEntity.ok(studentService.findByAgeBetween(age1, age2));
        }
        if (age1 != null) {
            return ResponseEntity.ok(studentService.findByAge(age1));
        }
        if (age2 != null) {
            return ResponseEntity.ok(studentService.findByAge(age2));
        }
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}/faculty")
    public Faculty getFaculty(@PathVariable Long id) {
        return studentService.getFacultyById(id);
    }


    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/query/count")
    public int getCountStudents() {
        return studentService.getCount();
    }

    @GetMapping("/query/avgAge")
    public float getAvgAge() {
        return studentService.getAvgAge();
    }

    @GetMapping("/query/lastStudents")
    public List<Student> getLastStudents() {
        return studentService.getLastStudents();
    }

    @GetMapping("/studentNameStartedWithA")
    public Stream<String> getStudentNameStartedWithA() {
        return studentService.getStudentNameStartedWithA();
    }

    @GetMapping("/avgAgeAllStudents")
    public double getAvgAgeAllStudents() {
        return studentService.getAvgAgeAllStudents();
    }

}
