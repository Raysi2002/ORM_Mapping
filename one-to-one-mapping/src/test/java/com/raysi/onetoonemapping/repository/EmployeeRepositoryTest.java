package com.raysi.onetoonemapping.repository;

import com.raysi.onetoonemapping.entity.Address;
import com.raysi.onetoonemapping.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
                    .state("Koshi")
                    .country("Nepal")
                    .build();
            Employee employee = Employee.builder()
                    .address(address)
                    .email("2002raysi@gmail.com")
                    .name("Aashish")
                    .build();
            employeeRepository.save(employee);

    }

    @Test
    public void saveEmployees(){

    }

    @Test
    public void fetchEmployee(){
        Employee employee = employeeRepository.findById(1L).get();
        System.out.println(employee);
    }

//    @Test
//    public void fetchEmployees(){
//        List<Employee> employees = employeeRepository.findAll();
//        System.out.println(employees);
//    }
}