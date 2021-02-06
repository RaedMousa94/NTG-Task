package com.example.demo.service;

import com.example.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();
    public Employee findById(int id);
    public Employee save(Employee employee);
    public void deleteById(int id);
    public void addChild(int managerId, int childId );
    public void removeChild(int managerId, int childId );
    public List<Employee> findManager();
    public List<Employee> findForSelectManagers();


}
