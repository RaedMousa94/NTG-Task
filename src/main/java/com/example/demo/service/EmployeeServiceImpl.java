package com.example.demo.service;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);
        Employee employee = null;
        if (result.isPresent()) {
            employee = result.get();
        } else {
            // no employee was found
            throw new RuntimeException("didn't find the employee id: " + id);
        }
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        Employee urr = employeeRepository.getOne(id);
        urr.getChildren().forEach(c -> {
            c.setManager(null);
            employeeRepository.save(c);
        });
        employeeRepository.delete(urr);
    }

    public void addChild(int managerId, int childId) {
        Employee manager = employeeRepository.getOne(managerId);
        Employee child = employeeRepository.getOne(childId);
        child.setManager(manager);
        employeeRepository.save(child);
    }

    public List<Employee> findManager() {
        List<Employee> managerIds = employeeRepository.findByManagerIdIsNull();
        return managerIds;
    }

    public List<Employee> findForSelectManagers() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

}
