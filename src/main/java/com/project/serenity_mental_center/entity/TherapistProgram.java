package com.project.serenity_mental_center.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "therapist_program")
public class TherapistProgram {
    @EmbeddedId
    private TherapistProgramId id;

    @ManyToOne
    @MapsId("therapistId")
    @JoinColumn(name="therapist_id")
    private Therapist therapistId;

    @ManyToOne
    @MapsId("programId")
    @JoinColumn(name="program_id")
    private TherapyProgram programId;

    String day;
    LocalTime startTime;
    LocalTime endTime;

}
