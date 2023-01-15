package ru.hogwarts.school.services;

import ru.hogwarts.school.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
    private Map<Long, Student> students = new HashMap<>();
    private long counter = 0;

    public Student addStudent(Student student){
        student.setId(++counter);
        students.put(counter, student);
        return student;
    }

    public Student findStudent(long id){
        return students.get(id);
    }

    public Student editStudent(Student student){
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(long id){
        return students.remove(id);
    }

    public Collection<Student> getAllStudents(){
        return students.values();
    }

    public Collection<Student> getStudentsByAge(int age){
        Set <Student>studentsByAge = new HashSet<>();
        for (Student student : students.values()) {
            if(student.getAge()==age){
                studentsByAge.add(student);
            }
        }
        return studentsByAge;
    }

}
