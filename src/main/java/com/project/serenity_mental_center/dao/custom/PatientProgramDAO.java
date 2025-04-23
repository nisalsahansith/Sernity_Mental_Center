package com.project.serenity_mental_center.dao.custom;

import com.project.serenity_mental_center.dao.CrudDAO;
import com.project.serenity_mental_center.entity.*;

import java.util.ArrayList;
import java.util.Date;

public interface PatientProgramDAO extends CrudDAO<PatientProgram> {
    public boolean save(PatientProgramId patientProgramId, String patientId, String programId, Date registerDate, String paymentId);
    public boolean update(PatientProgramId patientProgramId, String patientId, String programId, Date registerDate, String paymentId);
    public ArrayList<PatientProgram> getAllByID(String patientId);
}
