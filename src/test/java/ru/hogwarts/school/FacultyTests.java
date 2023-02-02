package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controllers.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyTests {
    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private FacultyRepository facultyRepository;


    @Autowired
    private TestRestTemplate restTemplate;

    Faculty testFaculty = new Faculty();


    @BeforeEach
    void setUp() {
        testFaculty.setName("Ля-ля-ля");
        testFaculty.setColor("желтый");
        facultyRepository.save(testFaculty);
    }

    @Test
    void contextLoadsFaculty() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();

    }

    @Test
    void createFacultyTest() throws Exception {
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculties", testFaculty, Faculty.class)).isNotNull();
    }

    @Test
    void findFacultyByIdTest() throws Exception {
        var faculty = this.restTemplate.getForObject("http://localhost:" + port + "/faculties/" + testFaculty.getId(), Faculty.class);

        Assertions.assertThat(faculty).isNotNull();
        Assertions.assertThat(faculty.getName()).isEqualTo(testFaculty.getName());

    }

    @Test
    void findFacultyByColorTest() throws Exception {
        var faculty = this.restTemplate.getForObject("http://localhost:" + port + "/faculties/" + testFaculty.getColor(), Faculty.class);

        Assertions.assertThat(faculty).isNotNull();
        Assertions.assertThat(faculty.getName()).isEqualTo(testFaculty.getName());
        Assertions.assertThat(faculty.getColor()).isEqualTo(testFaculty.getColor());
    }

    @Test
    void editFacultyTest() throws Exception {
        testFaculty.setName("Тру-ля-ля");
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculties", testFaculty, Faculty.class)).isEqualTo(testFaculty);
    }

    @Test
    void deleteFacultyTest() throws Exception {
        restTemplate.delete("http://localhost:" + port + "/faculties/"+ testFaculty.getId());
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculties/"+ testFaculty.getId(), Faculty.class)).isNull();
    }
}
