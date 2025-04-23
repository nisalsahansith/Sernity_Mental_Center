package com.project.serenity_mental_center.bo.custom;

import com.project.serenity_mental_center.bo.SuperBO;
import com.project.serenity_mental_center.dto.TherapyProgramDto;

import java.util.ArrayList;

public interface TherapyProgramBO extends SuperBO {
    public String getNextId();
    public ArrayList<TherapyProgramDto> getAllPrograms();
    public boolean saveTherapyProgram(TherapyProgramDto therapyProgramDto);
    public boolean updateTherapyProgram(TherapyProgramDto therapyProgramDto);
    public boolean deleteTherapy(String id);
    public ArrayList<String> getAllProgramId();
    public TherapyProgramDto getAllById(String programId);
    public double getFee(String programID) ;
}
