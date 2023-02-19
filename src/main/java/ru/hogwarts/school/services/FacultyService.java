package ru.hogwarts.school.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;

@Service
public class FacultyService {
    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        logger.debug("Вызван метод FacultyService");
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.debug("Вызван метод createFaculty");
        return facultyRepository.save(faculty);
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.debug("Вызван метод editFaculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.debug("Вызван метод deleteFaculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties() {
        logger.debug("Вызван метод getAllFaculties");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> findByColorOrNameIgnoreCase(String color, String name) {
        logger.debug("Вызван метод findByColorOrNameIgnoreCase");
        return facultyRepository.findByColorOrNameIgnoreCase(color, name);
    }

    public Faculty findFacultyById(long id) {
        logger.debug("Вызван метод findFacultyById");
        return facultyRepository.findById(id);
    }

    public Collection<Student> getStudents(long id) {
        logger.debug("Вызван метод getStudents");
        return facultyRepository.findById(id).getStudents();
    }

    public String getLongestFacultyName() {
        return facultyRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
    }

}
