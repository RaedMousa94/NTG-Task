package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="employee_info")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="gender")
    private String gender;

    @Column(name="location_name")
    private String locationName;

    @Column(name="profil_pic_url")
    private String profilePic;

    @Column(name="mobile_num")
    private String mobileNum;

    @Column(name="hour_cost")
    private String hourCost;

    @Column(name="skills")
    private String skills;

    @Column(name="status")
    private String status;


    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(name = "children_relation",
    joinColumns = {@JoinColumn (name = "mamager_id")},
    inverseJoinColumns = {@JoinColumn (name = "children_id")})
    private List<Employee> children;
}
