package com.raysi.manytomany.repository2;

import com.raysi.manytomany.enitity2.Post;
import com.raysi.manytomany.enitity2.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void savePost(){
        List<Tag> tags = new ArrayList<>();
        Tag tag = Tag.builder()
                .tagName("LifeLine")
                .build();
        Tag tag1 = Tag.builder()
                .tagName("Love")
                .build();
        tags.add(tag1);
        tags.add(tag);
        Post post = Post.builder()
                .postContent("I Love You Bae!")
                .tags(tags)
                .postName("Our Love journey")
                .build();

        postRepository.save(post);
    }

    public void fetchPost(){
    }

}