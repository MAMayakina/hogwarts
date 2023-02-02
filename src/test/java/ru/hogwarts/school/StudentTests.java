package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controllers.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentTests {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    Student testStudent = new Student();

    @BeforeEach
    void setUp() {
        testStudent.setName("Гриша");
        testStudent.setAge(20);
        studentRepository.save(testStudent);
    }

    @Test
    void contextLoadsStudent() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }


    @Test
    void createStudentTest() throws Exception {
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/students", testStudent, Student.class)).isNotNull();
    }

    @Test
    void findStudentsByIdTest() throws Exception {
        var student = this.restTemplate.getForObject("http://localhost:" + port + "/students/" + testStudent.getId(), Student.class);

        Assertions.assertThat(student).isNotNull();
        Assertions.assertThat(student.getName()).isEqualTo(testStudent.getName());
    }

    @Test
    void findStudentsByAgeTest() throws Exception {
        var student = this.restTemplate.getForObject("http://localhost:" + port + "/students/" + testStudent.getAge(), Student.class);

        Assertions.assertThat(student).isNotNull();
        Assertions.assertThat(student.getName()).isEqualTo(testStudent.getName());
        Assertions.assertThat(student.getAge()).isEqualTo(testStudent.getAge());
    }

    @Test
    void editStudentTest() throws Exception {
        testStudent.setName("Григорий");
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/students", testStudent, Student.class)).isEqualTo(testStudent);
    }

    @Test
    void deleteStudentTest() throws Exception {
        restTemplate.delete("http://localhost:" + port + "/students/"+ testStudent.getId());
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/"+ testStudent.getId(), Student.class)).isNull();
    }

}
