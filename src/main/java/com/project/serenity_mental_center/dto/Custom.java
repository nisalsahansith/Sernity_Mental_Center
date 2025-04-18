package com.project.serenity_mental_center.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Custom {
    private String day;
    private String availableTimeList;

    private String therapistId;
    private String Day;
    public LocalTime start;
    public LocalTime end;


    public Custom(String day, String availableTimeList) {
        this.day = day;
        this.availableTimeList = availableTimeList;
    }

    public Custom(LocalTime end, LocalTime start, String day, String therapistId) {
        this.end = end;
        this.start = start;
        this.Day = day;
        this.therapistId = therapistId;
    }

    public Custom(LocalTime start, LocalTime end, String day) {
        this.start = start;
        this.end = end;
        this.day = day;
    }
    public Custom(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return start.format(formatter) + " - " + end.format(formatter);
    }
}
