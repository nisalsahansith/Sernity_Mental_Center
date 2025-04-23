package com.project.serenity_mental_center.dao.custom;

import com.project.serenity_mental_center.dao.CrudDAO;
import com.project.serenity_mental_center.entity.TherapyProgram;

import java.util.List;

public interface TherapyProgramDAO extends CrudDAO<TherapyProgram> {
    public List<TherapyProgram> getAllId();
    public double getFee(String programID);
}
