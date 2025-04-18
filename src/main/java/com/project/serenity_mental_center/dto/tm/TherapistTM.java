package com.project.serenity_mental_center.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TherapistTM {
    String id;
    String name;
    String phone;
    String email;
    String specialization;
}
