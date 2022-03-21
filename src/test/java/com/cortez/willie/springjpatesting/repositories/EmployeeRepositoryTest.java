package com.cortez.willie.springjpatesting.repositories;

import com.cortez.willie.springjpatesting.entities.Employee;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .firstName("Willie")
                .lastName("Cortez")
                .email("wc@gmail.com")
                .build();
    }

    @Test
    void savedEmployeeTest(){
//        Employee employee = Employee.builder()
//                .firstName("Willie")
//                .lastName("Cortez")
//                .email("wc@gmail.com")
//                .build();

        Employee savedEmployee = employeeRepository.save(employee);
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
        assertThat(savedEmployee.getFirstName().getBytes());
    }

    @Test
    public void givenEmployeeList_whenFindAll_thenEmployeesList(){
        //given
//        Employee employee = Employee.builder()
//                .firstName("Willie")
//                .lastName("Cortez")
//                .email("wc@gmail.com")
//                .build();
        Employee employee1 = Employee.builder()
                .firstName("Willie")
                .lastName("Cortez")
                .email("wc@gmail.com")
                .build();
       employeeRepository.save(employee);
       employeeRepository.save(employee1);
        //when
        List<Employee> employeeList = employeeRepository.findAll();
        //then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @DisplayName("Get employee by ID")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject(){
        //GIVEN precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Willie")
//                .lastName("Cortez")
//                .email("wc@gmail.com")
//                .build();
        employeeRepository.save(employee);
        //WHEN action or the behaviour that is going to be test
        Employee employee2 = employeeRepository.findById(employee.getId()).get();
        //THEN verify the output
        assertThat(employee2).isNotNull();
    }


    @DisplayName("Get employee by email")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject(){
        //GIVEN precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Willie")
//                .lastName("Cortez")
//                .email("wc@gmail.com")
//                .build();
        employeeRepository.save(employee);
        //WHEN action or the behaviour that is going to be test
        Employee employee1 = employeeRepository.findByEmail(employee.getEmail()).get();
        //THEN verify the output
        assertThat(employee1).isNotNull();
    }


    @DisplayName("Update employee object operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployeeObject(){
        //GIVEN precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Willie")
//                .lastName("Cortez")
//                .email("wc@gmail.com")
//                .build();
        employeeRepository.save(employee);
        //WHEN action or the behaviour that is going to be test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("cw@gmail.com");
        savedEmployee.setLastName("James");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);
        //THEN verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("cw@gmail.com");
        assertThat(updatedEmployee.getLastName()).isEqualTo("James");
    }

    @DisplayName("Delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployeeObject(){
        //GIVEN precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Willie")
//                .lastName("Cortez")
//                .email("wc@gmail.com")
//                .build();
        employeeRepository.save(employee);
        //WHEN action or the behaviour that is going to be test
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
        //THEN verify the output
        assertThat(optionalEmployee).isEmpty();
    }

    @DisplayName("Custom JPQL test")
    @Test
    public void givenFirstNamedAndLastName_whenFindByJPQL_thenReturnEmployeeObject(){
        //GIVEN precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Willie")
//                .lastName("Cortez")
//                .email("wc@gmail.com")
//                .build();
        employeeRepository.save(employee);
        String firstName = "Willie";
        String lastName = "Cortez";
        //WHEN action or the behaviour that is going to be test
        Employee savedEmployee = employeeRepository.findByJPQL(firstName, lastName);
        //THEN verify the output
        assertThat(savedEmployee).isNotNull();
    }

    @DisplayName("Custom query using named parameters")
    @Test
    public void given_when_then(){
        //GIVEN precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Willie")
//                .lastName("Cortez")
//                .email("wc@gmail.com")
//                .build();
        employeeRepository.save(employee);
        String firstName = "Willie";
        String lastName = "Cortez";
        //WHEN action or the behaviour that is going to be test
        Employee savedEmployee = employeeRepository.findByJPQLNamedParams(firstName, lastName);
        //THEN verify the output
        assertThat(savedEmployee).isNotNull();
    }

    @DisplayName("Custom query using native query")
    @Test
    public void givenEmployee_whenNativeQuery_thenReturnEmployeeNativeQuery(){
        //GIVEN precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Willie")
//                .lastName("Cortez")
//                .email("wc@gmail.com")
//                .build();
        employeeRepository.save(employee);
        //WHEN action or the behaviour that is going to be test
        Employee savedEmployee3 = employeeRepository.findByNativeQuery(employee.getFirstName(), employee.getLastName());
        //THEN verify the output
        assertThat(savedEmployee3).isNotNull();
    }

    @DisplayName("Custom query named param native query")
    @Test
    public void givenSaveEmployeeObject_whenNativeQueryNamedParams_thenReturnNativeQueryEmployeeObject(){
        //GIVEN precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Willie")
//                .lastName("Cortez")
//                .email("wc@gmail.com")
//                .build();
        employeeRepository.save(employee);
        //WHEN action or the behaviour that is going to be test
        Employee savedEmployee4 = employeeRepository.findByNativeSQLNamedParam(employee.getFirstName(), employee.getLastName());
        //THEN verify the output
        assertThat(savedEmployee4).isNotNull();
    }
}