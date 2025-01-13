package com.raysi.onetoonemapping.repository_unidirectional;

import com.raysi.onetoonemapping.entity_unidirectional.Address;
import com.raysi.onetoonemapping.entity_unidirectional.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class EmployeeRepositoryTest {
    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeRepositoryTest(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Test
    public void saveData(){

        Address address = Address.builder()
                .pinCode("116611")
                .city("Laukahi")
                .state("Koshi Province")
                .country("Nepal")
                .build();

        Employee employee = Employee.builder()
                .name("Aashish")
                .email("2002raysi@gmail.com")
                .address(address)
                .build();

        employeeRepository.save(employee);
    }

    @Test
    public void fetchEmployee(){
        List<Employee> employees = employeeRepository.findAll();
        if(employees.isEmpty())
            System.out.println("No Employees available");
        else
            System.out.println(employees);
    }

    @Test
    public void deleteEmployee(){
        employeeRepository.deleteById(1L);
    }
}