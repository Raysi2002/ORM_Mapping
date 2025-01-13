package com.raysi.onetoonemapping.repository_bidirectional;

import com.raysi.onetoonemapping.entity_bidirectional.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
