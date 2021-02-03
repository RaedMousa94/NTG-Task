package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
@Data
@Builder
public class UserDto {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String userName;

    private String password;

    private String gender;

    private String locationName;

    private String profilePic;

    private String mobileNum;

    private String hourCost;

    private String skills;

    private String status;

    private Integer managerId;
}
