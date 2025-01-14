package com.raysi.manytooneandonetomany.repository2;

import com.raysi.manytooneandonetomany.entity2.Blog;
import com.raysi.manytooneandonetomany.entity2.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveBlog(){
        Blog blog = Blog.builder()
                .heading("Our Journey")
                .content("I Love you bae")
                .build();

        User user = User.builder()
                .userName("Aashish")
                .email("2002raysi@gmail.com")
                .build();
        user.addBlog(blog);
        userRepository.save(user);
    }

    @Test
    public void fetchBlog(){
        Optional<User> user = userRepository.findById(1L);
        System.out.println(user.stream().toList());
    }

    @Test
    public void deleteBlog(){
        userRepository.deleteById(1L);
    }
}