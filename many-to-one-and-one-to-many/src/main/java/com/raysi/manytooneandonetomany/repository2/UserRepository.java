package com.raysi.manytooneandonetomany.repository2;

import com.raysi.manytooneandonetomany.entity2.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
