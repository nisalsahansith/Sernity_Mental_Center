package com.project.serenity_mental_center.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TherapyProgramDto {
    String id;
    String name;
    String duration;
    double cost;
    String description;
}
