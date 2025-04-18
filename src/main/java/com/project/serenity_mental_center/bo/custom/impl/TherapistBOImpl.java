package com.project.serenity_mental_center.bo.custom.impl;

import com.project.serenity_mental_center.dao.custom.impl.TherapistDAOImpl;
import com.project.serenity_mental_center.dto.TherapistDto;
import com.project.serenity_mental_center.dto.TherapistProgramDto;
import com.project.serenity_mental_center.entity.Therapist;
import com.project.serenity_mental_center.entity.TherapistProgram;
import com.project.serenity_mental_center.entity.TherapyProgram;

import java.util.ArrayList;
import java.util.List;

public class TherapistBOImpl {
    private TherapistDAOImpl therapistDAO = new TherapistDAOImpl();

    public ArrayList<TherapistDto> getAllTherapist() {
        List<Therapist> therapists = therapistDAO.getAllTherapist();
        ArrayList<TherapistDto> therapistDtoList = new ArrayList<TherapistDto>();
        for (Therapist therapist : therapists) {
            TherapistDto therapistDto = new TherapistDto(
                    therapist.getId(),
                    therapist.getName(),
                    therapist.getPhone(),
                    therapist.getEmail(),
                    therapist.getSpecialization()
            );
            therapistDtoList.add(therapistDto);
        }
        return therapistDtoList;
    }

    public boolean saveTherapist(TherapistDto therapistDto) {
        return therapistDAO.save(new Therapist(
                therapistDto.getId(),
                therapistDto.getName(),
                therapistDto.getPhone(),
                therapistDto.getEmail(),
                therapistDto.getSpecialization()
        ));
    }

    public boolean updateTherapist(TherapistDto therapistDto) {
        return therapistDAO.update(new Therapist(
                therapistDto.getId(),
                therapistDto.getName(),
                therapistDto.getPhone(),
                therapistDto.getEmail(),
                therapistDto.getSpecialization()
        ));
    }

    public boolean deletePatient(String id) {
        return therapistDAO.delete(id);
    }

    public String getNextId() {
        String lastId = therapistDAO.getLastId();
        if (lastId != null) {
            String subString = lastId.substring(1);
            int lastIndex = Integer.parseInt(subString);
            int nextIndex = lastIndex + 1;
            return String.format("T%03d", nextIndex);
        }
        return "T001";
    }

    public ArrayList<String> getAllTherapistId(){
        ArrayList<TherapistDto> therapistDtos = getAllTherapist();
        ArrayList<String> IDS = new ArrayList<>();
        for (TherapistDto therapistDto: therapistDtos){
            IDS.add(therapistDto.getId());
        }
        return IDS;
    }


    public TherapistDto getAllById(String therapistId) {
        ArrayList<TherapistDto> therapistDtos = getAllTherapist();
        for (TherapistDto therapistDto: therapistDtos){
            if (therapistDto.getId().equals(therapistId)){
                return therapistDto;
            }
        }
        return null;
    }

}
