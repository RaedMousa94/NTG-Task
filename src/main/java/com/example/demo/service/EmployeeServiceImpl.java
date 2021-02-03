package com.example.demo.service;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.dto.DataEmployeeDto;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.SelectManagerDto;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Employee urr = employeeRepository.getOne(childId);

        employeeRepository.deleteById(id);
    }

    public void addChild(int managerId, int childId) {
        Employee manager = employeeRepository.getOne(managerId);
        Employee child = employeeRepository.getOne(childId);
        manager.getChildren().add(child);
        employeeRepository.save(manager);
    }

    public void removeChild(int managerId, int childId) {
        Employee manager = employeeRepository.getOne(managerId);
        manager.getChildren().removeIf(employee -> employee.getId() == childId);
        employeeRepository.save(manager);
    }

    public List<Employee> findManager(){
        List<Employee> managerIds  = employeeRepository.findMangerId();
        return managerIds;
    }

    public List<Employee> findForSelectManagers(){
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

}
