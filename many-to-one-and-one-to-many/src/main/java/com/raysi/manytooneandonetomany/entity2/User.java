package com.raysi.manytooneandonetomany.entity2;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long userId;
    public String userName;
    @Email
    private String email;

    @Builder.Default
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<Blog> blogs= new ArrayList<>();

    public void addBlog(Blog blog){
        blogs.add(blog);
        blog.setUser(this);
    }

    public void addBlogs(List<Blog> blogs){
        blogs.forEach(this :: addBlog);
    }

    public void removeBlog(Blog blog){
        blogs.remove(blog);
        blog.setUser(null);
    }

    public void removeBlogs(List<Blog> blogs){
        blogs.forEach(this :: removeBlog);
    }

    @Override
    public String toString() {
        return "\nID: " + this.getUserName() +
                "\nUsername: " + this.getUserName() +
                "\nEmail: " + this.getEmail() +
                "\nBlogs\n: " + this.getBlogs();
    }
}
