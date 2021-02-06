package com.example.demo.dao;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee , Integer> {

    @Query(value = "SELECT distinct a.* FROM employee_info as a left join children_relation as b on a.id = b.mamager_id where a.id not in (select c.children_id from children_relation c)",nativeQuery = true)
    public List<Employee> findMangerId();

    public Optional<Employee> findByUserName(String userName);

}
