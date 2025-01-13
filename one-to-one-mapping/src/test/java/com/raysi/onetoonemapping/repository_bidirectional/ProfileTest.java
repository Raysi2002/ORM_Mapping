package com.raysi.onetoonemapping.repository_bidirectional;

import com.raysi.onetoonemapping.entity_bidirectional.Profile;
import com.raysi.onetoonemapping.entity_bidirectional.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProfileTest {
    private final UserRepository userRepository;
    @Autowired
    public ProfileTest(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Test
    public void saveUser(){

        Profile profile = Profile.builder().build();


        User user = User.builder()
                .userName("Aashish")
                .dob(LocalDate.of(2002, 8, 24))
                .profile(profile)
                .build();

        userRepository.save(user);
    }

    @Test
    public void fetchUser(){
        Optional<User> user = userRepository.findById(2L);
        System.out.println(user);
    }

    @Test
    public void deleteUser(){
        userRepository.deleteById(2L);
    }

}