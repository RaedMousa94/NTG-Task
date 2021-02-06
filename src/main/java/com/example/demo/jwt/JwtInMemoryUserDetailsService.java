package com.example.demo.jwt;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

//    static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();
//
//    static {
//        inMemoryUserList.add(new JwtUserDetails(1L, "in28minutes",
//                "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
//        inMemoryUserList.add(new JwtUserDetails(2L, "ranga",
//                "$2a$10$IetbreuU5KihCkDB6/r1DOJO0VyU9lSiBcrMDT.biU7FOt2oqZDPm", "ROLE_USER_2"));
//
//        //$2a$10$IetbreuU5KihCkDB6/r1DOJO0VyU9lSiBcrMDT.biU7FOt2oqZDPm
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Employee> employee1 =  employeeRepository.findByUserName(username);

        UserDetails userDetail = new JwtUserDetails(employee1.get().getId()*1L,employee1.get().getUserName(),employee1.get().getPassword(),"ROLE");
        if (employee1.isEmpty()) {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
        }

        return userDetail;
    }

}
