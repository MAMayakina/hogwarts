package ru.hogwarts.school.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.debug("Вызван метод createStudent");
        return studentRepository.save(student);
    }

    public Student editStudent(Student student) {
        logger.debug("Вызван метод editStudent");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.debug("Вызван метод deleteStudent");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        logger.debug("Вызван метод getAllStudents");
        return studentRepository.findAll();
    }

    public Collection<Student> findByAgeBetween(int ageMin, int ageMax) {
        logger.debug("Вызван метод findByAgeBetween");
        return studentRepository.findByAgeBetween(ageMin, ageMax);
    }

    public Collection<Student> findByAge(int age) {
        logger.debug("Вызван метод findByAge");
        return studentRepository.findByAge(age);
    }

    public Student findById(long id) {
        logger.debug("Вызван метод findById");
        return studentRepository.findById(id);
    }

    public Faculty getFacultyById(long id) {
        logger.debug("Вызван метод getFacultyById");
        return studentRepository.findById(id).getFacultyId();
    }

    public int getCount() {
        logger.debug("Вызван метод getCount");
        return studentRepository.getCount();
    }

    public float getAvgAge() {
        logger.debug("Вызван метод getAvgAge");
        return studentRepository.getAvgAge();
    }

    public List<Student> getLastStudents() {
        logger.debug("Вызван метод getLastStudents");
        return studentRepository.getLastStudents();
    }

    public Stream<String> getStudentNameStartedWithA() {
        return studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("А"))
                .sorted();
    }

    public double getAvgAgeAllStudents() {
        return studentRepository.findAll()
                .stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElse(0D);
    }


    public void printStudents() {
        List<Student> students = studentRepository.findAll(PageRequest.of(0, 6)).getContent();

        printStudents(students.subList(0, 2));

        new Thread(()->printStudents(students.subList(2,4))).start();
        new Thread(()->printStudents(students.subList(4,6))).start();
    }

    public void printStudents(List<Student> students) {
        for (Student student:students){
            logger.info(student.getName());
        }
    }

    public void printStudentsSync() {
        List<Student> students = studentRepository.findAll(PageRequest.of(0, 6)).getContent();

        printStudentsSync(students.subList(0, 2));

        new Thread(()->printStudentsSync(students.subList(2,4))).start();
        new Thread(()->printStudentsSync(students.subList(4,6))).start();
    }

    public synchronized void printStudentsSync(List<Student> students) {
        for (Student student:students){
          logger.info(student.getName());
        }
    }

}
