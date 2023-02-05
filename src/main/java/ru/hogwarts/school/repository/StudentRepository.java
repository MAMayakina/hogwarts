package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int ageMin, int ageMax);
    Collection<Student> findByAge(int age);
    Student findById(long id);

    @Query(value="Select COUNT(*) from student", nativeQuery = true)
    int getCount();

    @Query(value="Select AVG(age) from student", nativeQuery = true)
    float getAvgAge();

    @Query(value="Select * from student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> getLastStudents();


}
