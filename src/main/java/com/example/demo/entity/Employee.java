package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
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


    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "manager_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Employee manager ;



    @JsonIgnore
    @OneToMany(mappedBy = "manager",fetch =FetchType.LAZY )
    private Set<Employee> children = new HashSet<>();

}
