package com.raysi.manytomany.enitity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private String studentName;
    @ManyToMany(mappedBy = "students", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Course> courses = new ArrayList<>();

    public String debugInfo() {
        return "Student ID: " + this.studentId +
                ", Student Name: " + this.studentName +
                ", Courses: " + this.courses.stream()
                .map(Course::getCourseName)
                .toList();
    }
}
