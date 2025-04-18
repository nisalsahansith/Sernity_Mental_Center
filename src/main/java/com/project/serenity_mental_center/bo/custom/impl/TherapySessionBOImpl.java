package com.project.serenity_mental_center.bo.custom.impl;

import com.project.serenity_mental_center.dao.custom.impl.TherapySessionDAOImpl;
import com.project.serenity_mental_center.dto.TherapySessionDTO;
import com.project.serenity_mental_center.entity.TherapySession;

import java.util.ArrayList;

public class TherapySessionBOImpl {
    TherapySessionDAOImpl therapySessionDAO = new TherapySessionDAOImpl();

    public ArrayList<TherapySessionDTO> getAll() {
        ArrayList<TherapySession> therapySessions = therapySessionDAO.getAll();
        ArrayList<TherapySessionDTO> therapySessionDTOS = new ArrayList<>();
        for (TherapySession therapySession : therapySessions){
            TherapySessionDTO therapySessionDTO = new TherapySessionDTO(
                    therapySession.getId(),
                    therapySession.getDate(),
                    therapySession.getStartTime(),
                    therapySession.getEndTime(),
                    therapySession.getTherapist().getId(),
                    therapySession.getPatient().getId(),
                    therapySession.getTherapyProgram().getId()
            );
            therapySessionDTOS.add(therapySessionDTO);
        }
        return therapySessionDTOS;
    }

    public String getNextSessionId() {
        String lastId = therapySessionDAO.getLastId();
        if (lastId != null) {
            String subString = lastId.substring(1);
            int lastIndex = Integer.parseInt(subString);
            int nextIndex = lastIndex + 1;
            return String.format("TS%03d", nextIndex);
        }
        return "TS001";
    }
}
