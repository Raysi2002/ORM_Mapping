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
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String courseName;
    private Integer courseDuration;

    @Builder.Default
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    public String debugInfo() {
        return "Course ID: " + this.courseId +
                ", Course Name: " + this.courseName +
                ", Students: " + this.students.stream()
                .map(Student::getStudentName)
                .toList();
    }
}
