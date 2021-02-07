package com.example.demo.rest;

import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ntg")
@CrossOrigin(origins="*", maxAge=3600)
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;



}
