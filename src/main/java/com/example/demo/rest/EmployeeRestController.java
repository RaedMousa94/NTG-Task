package com.example.demo.rest;

import com.example.demo.dto.DataEmployeeDto;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.SelectManagerDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/ntg")
@CrossOrigin("http://localhost:4200")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;



    @GetMapping("/employee")
    public List<EmployeeDto> findAll() {

        List<Employee> employees = employeeService.findManager();

        return adapter(employees);
    }

    List<EmployeeDto> adapter(List<Employee> list){
        if(list==null || list.isEmpty()){
            return new ArrayList<>();
        }
        List<EmployeeDto> ret = new ArrayList<>();
        for (Employee e : list) {
            DataEmployeeDto dataEmployeeDto = DataEmployeeDto.builder()
                    .email(e.getEmail())
                    .status(e.getStatus())
                    .firstName(e.getFirstName())
                    .hourCost(e.getHourCost())
                    .id(e.getId())
                    .build();
            List<EmployeeDto> children = adapter(new ArrayList<>(e.getChildren()));

            EmployeeDto employeeDto = EmployeeDto.builder().data(dataEmployeeDto).children(children).build();
            ret.add(employeeDto);
        }
        return ret;
    }



    @GetMapping("/select_manager")
    public List<SelectManagerDto> findForSelectManager() {

        List<Employee> employees = employeeService.findAll();

        List<SelectManagerDto> selectManagerDtoList = employees.stream().map(m ->{
            return SelectManagerDto.builder()
                    .firstName(m.getFirstName())
                    .id(m.getId()).build();
        }).collect(Collectors.toList());

        return selectManagerDtoList;
    }



    @GetMapping("/employee/{id}")
    public Employee findById(@PathVariable int id) {
        return employeeService.findById(id);
    }

//    @GetMapping("/manager")
//    public List<EmployeeDto> findManager() {
//        List<Employee> manager = employeeService.findAll();
//
//        List<EmployeeDto> managerDtoList = manager.stream().map(m -> {
//            return EmployeeDto.builder().data(DataEmployeeDto.builder()
//                    .firstName(m.getFirstName())
//                    .email(m.getEmail())
//                    .hourCost(m.getHourCost())
//                    .id(m.getId())
//                    .status(m.getStatus()).build()).build();
//        }).collect(Collectors.toList());
//
//        return managerDtoList;
//    }


    @PostMapping("/employee")
    public void save(@RequestBody UserDto userDto) {
        Employee employee = Employee.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .userName(userDto.getUserName())
                .locationName(userDto.getLocationName())
                .password(userDto.getPassword())
                .mobileNum(userDto.getMobileNum())
                .email(userDto.getEmail())
                .gender(userDto.getGender())
                .hourCost(userDto.getHourCost())
                .status(userDto.getStatus())
                .skills(userDto.getSkills())
                .profilePic(userDto.getProfilePic()).build();

        employee = employeeService.save(employee);

        if (userDto.getManagerId() != null){
            employeeService.addChild(userDto.getManagerId(), employee.getId());
        }
    }



    @DeleteMapping("/employee/{id}")
    public void delete(@PathVariable int id) {


        employeeService.deleteById(id);
    }

    @PutMapping("employee/{id}")
    public void save(@PathVariable int id ,@RequestBody UserDto userDto) {
        Employee employee = Employee.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .userName(userDto.getUserName())
                .locationName(userDto.getLocationName())
                .password(userDto.getPassword())
                .mobileNum(userDto.getMobileNum())
                .email(userDto.getEmail())
                .gender(userDto.getGender())
                .hourCost(userDto.getHourCost())
                .status(userDto.getStatus())
                .skills(userDto.getSkills())
                .profilePic(userDto.getProfilePic()).build();

        employee = employeeService.save(employee);

        if (userDto.getManagerId() != null){
            employeeService.addChild(userDto.getManagerId(), employee.getId());
        }
    }


}
