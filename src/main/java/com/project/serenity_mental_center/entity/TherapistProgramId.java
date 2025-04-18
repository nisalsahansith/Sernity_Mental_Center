package com.project.serenity_mental_center.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TherapistProgramId {
    private String therapistId;
    private String programId;
}
