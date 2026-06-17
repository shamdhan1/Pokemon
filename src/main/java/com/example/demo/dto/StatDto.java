package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatDto {
    private String name;
    private Integer value;
}
