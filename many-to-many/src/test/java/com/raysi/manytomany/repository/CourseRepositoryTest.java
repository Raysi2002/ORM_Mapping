package com.raysi.manytomany.repository;

import com.raysi.manytomany.enitity.Course;
import com.raysi.manytomany.enitity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void saveData(){
        Student student = Student.builder()
                .studentName("Kanxu")
                .build();
        List<Student> students = new ArrayList<>();
        students.add(student);
        Course course = Course.builder()
                .courseName("JAVA")
                .courseDuration(1)
                .students(students)
                .build();
        courseRepository.save(course);
    }

    @Test
    public void fetchCourse(){
        Optional<Course> course = courseRepository.findById(1L);
        System.out.println(course.map(Course::debugInfo).orElse("Course not found"));
    }

    @Test
    public void deleteCourse(){
        Course course = courseRepository.findById(2L).orElseThrow(RuntimeException :: new);
        for(Student student : course.getStudents()){
            student.getCourses().remove(course);
        }
        courseRepository.delete(course);
    }
}