package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.*;

@Service
public class FacultyService {
    private Map<Long, Faculty> faculties = new HashMap<>();
    private long counter = 0;

    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(++counter);
        faculties.put(counter, faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        return faculties.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(long id) {
        return faculties.remove(id);
    }

    public Collection<Faculty> getAllFaculties(){
        return faculties.values();
    }

    public Collection<Faculty> getFacultiesByColor(String color){
        Set<Faculty> facultiesByColor = new HashSet<>();
        for (Faculty faculty : faculties.values()) {
            if(faculty.getColor().equals(color)){
                facultiesByColor.add(faculty);
            }
        }
        return facultiesByColor;
    }

}
