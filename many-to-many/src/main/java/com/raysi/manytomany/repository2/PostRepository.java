package com.raysi.manytomany.repository2;

import com.raysi.manytomany.enitity2.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
