package com.example.demo.rest;

import com.example.demo.dto.DataEmployeeDto;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.SelectManagerDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/ntg")
@CrossOrigin(origins="*", maxAge=3600)
public class ManagerRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/manager/list")
    public List<SelectManagerDto> findForSelectManager() {
        List<Employee> employees = employeeService.findAll();
        List<SelectManagerDto> selectManagerDtoList = employees.stream().map(m ->{
            return SelectManagerDto.builder()
                    .firstName(m.getFirstName())
                    .id(m.getId()).build();
        }).collect(Collectors.toList());
        return selectManagerDtoList;
    }

    @GetMapping("/manager")
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


    @GetMapping("/employee/{id}")
    public UserDto findById(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        UserDto userDto = UserDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .userName(employee.getUserName())
                .mobileNum(employee.getMobileNum())
                .password(employee.getPassword())
                .gender(employee.getGender())
                .email(employee.getEmail())
                .hourCost(employee.getHourCost())
                .locationName(employee.getLocationName())
                .gender(employee.getGender())
                .skills(employee.getSkills())
                .status(employee.getStatus())
                .build();
        if (employee.getManager() != null) {
            userDto.setManagerId(employee.getManager().getId());
        }
        return userDto;
    }

    @PostMapping("/employee")
    public void save(@RequestBody UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Employee employee = Employee.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .userName(userDto.getUserName())
                .locationName(userDto.getLocationName())
                .password(encoder.encode(userDto.getPassword()))
                .mobileNum(userDto.getMobileNum())
                .email(userDto.getEmail())
                .gender(userDto.getGender())
                .hourCost(userDto.getHourCost())
                .status(userDto.getStatus())
                .skills(userDto.getSkills())
                .profilePic(userDto.getProfilePic()).build();


        if (userDto.getManagerId() != null) {
            Employee manager = employeeService.findById(userDto.getManagerId());
            employee.setManager(manager);
        }
        employee = employeeService.save(employee);
    }

    @DeleteMapping("/employee/{id}")
    public void delete(@PathVariable int id) {
        employeeService.deleteById(id);
    }

    @PutMapping("employee/{id}")
    public void save(@PathVariable int id, @RequestBody UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Employee employee = Employee.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .userName(userDto.getUserName())
                .locationName(userDto.getLocationName())
                .password(encoder.encode(userDto.getPassword()))
                .mobileNum(userDto.getMobileNum())
                .email(userDto.getEmail())
                .gender(userDto.getGender())
                .hourCost(userDto.getHourCost())
                .status(userDto.getStatus())
                .skills(userDto.getSkills())
                .profilePic(userDto.getProfilePic()).build();

        if (userDto.getManagerId() != null) {
            Employee manager = employeeService.findById(userDto.getManagerId());
            employee.setManager(manager);
        }
        employee = employeeService.save(employee);
    }

}
