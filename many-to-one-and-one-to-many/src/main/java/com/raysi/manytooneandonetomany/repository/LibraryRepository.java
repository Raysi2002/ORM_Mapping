package com.raysi.manytooneandonetomany.repository;

import com.raysi.manytooneandonetomany.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}
