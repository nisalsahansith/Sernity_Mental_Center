package com.project.serenity_mental_center.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TherapistProgramTM {
    private String programId;
    private String therapistId;
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
}
