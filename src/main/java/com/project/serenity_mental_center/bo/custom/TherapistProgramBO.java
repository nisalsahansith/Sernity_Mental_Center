package com.project.serenity_mental_center.bo.custom;

import com.project.serenity_mental_center.bo.SuperBO;
import com.project.serenity_mental_center.dto.CustomDto;
import com.project.serenity_mental_center.dto.TherapistProgramDto;
import com.project.serenity_mental_center.entity.TherapistProgram;
import com.project.serenity_mental_center.entity.TherapistProgramId;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public interface TherapistProgramBO extends SuperBO {
    public boolean saveTherapistProgram(String programId, String therapistId, String day, LocalTime startTime, LocalTime endTime);
    public ArrayList<TherapistProgramDto> getAllTherapistProgram();
    public boolean updateTherapistProgram(String programId, String therapistId, String day, LocalTime startTime, LocalTime endTime);
    public boolean deleteTherapistProgram(String programId, String therapistId);
    public ArrayList<String> getTherapistId(String programId);
    public ArrayList<CustomDto> getAvailableTime(String therapistID);
    List<CustomDto> findAvailableSlots(LocalTime startOfDay, LocalTime endOfDay, List<CustomDto> bookedSlots);
    List<String> getAllDaysOfWeek();
}
