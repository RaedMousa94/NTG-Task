package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class EmployeeDto {

    private DataEmployeeDto data;
    private List<EmployeeDto> children;
}
