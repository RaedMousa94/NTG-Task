package com.example.demo.dao;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee , Integer> {

    public List<Employee> findByManagerIdIsNull();

    public Optional<Employee> findByUserName(String userName);

}
