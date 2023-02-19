package ru.hogwarts.school.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;
import java.util.stream.Stream;

@Service
public class InfoService {

    private static final Logger LOG = LoggerFactory.getLogger(InfoService.class);

    private final StudentRepository studentRepository;

    public InfoService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void testParallelStream() {
        long start = System.currentTimeMillis();
        Stream.iterate(1L, a -> a + 1)
                .limit(10_000L)
                .reduce(0L, (a, b) -> {
                    long result = 0;
                    for (int i = 0; i < a + b; i++) {
                        result += 1;
                    }
                    return result;
                });
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        Stream.iterate(1L, a -> a + 1)
                .parallel()
                .limit(10_000L)
                .reduce(0L, (a, b) -> {
                    long result = 0;
                    for (int i = 0; i < a + b; i++) {
                        result += 1;
                    }
                    return result;
                });
        System.out.println(System.currentTimeMillis() - start);
    }


    public void printStudents() {
        List<Student> students = studentRepository.findAll(PageRequest.of(0, 6)).getContent();

        printStudents(students.subList(0, 2));

        new Thread(()->printStudents(students.subList(2,4))).start();
        new Thread(()->printStudents(students.subList(4,6))).start();
    }

    public void printStudents(List<Student> students) {
        for (Student student:students){
            LOG.info(student.getName());
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
            LOG.info(student.getName());
        }
    }
}