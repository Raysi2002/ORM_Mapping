package com.raysi.onetoonemapping.repository_unidirectional;

import com.raysi.onetoonemapping.entity_unidirectional.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
