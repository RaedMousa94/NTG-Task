package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelectManagerDto {

    private int id;

    private String firstName;
}
