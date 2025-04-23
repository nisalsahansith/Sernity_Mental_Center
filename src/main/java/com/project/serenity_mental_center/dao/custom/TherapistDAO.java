package com.project.serenity_mental_center.dao.custom;

import com.project.serenity_mental_center.dao.CrudDAO;
import com.project.serenity_mental_center.entity.Therapist;
import com.project.serenity_mental_center.entity.TherapyProgram;

import java.util.List;

public interface TherapistDAO extends CrudDAO<Therapist> {
    public List<TherapyProgram> getAllId();
}
