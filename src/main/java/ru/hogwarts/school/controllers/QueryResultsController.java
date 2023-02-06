package ru.hogwarts.school.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.services.StudentService;

@RestController
public class QueryResultsController {
    private final StudentService studentService;

    public QueryResultsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        return studentService.getFacultyById(id);
    }
}
