package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataEmployeeDto {

    private int id;

    private String firstName;

    private String email;

    private String hourCost;

    private String status;

}
