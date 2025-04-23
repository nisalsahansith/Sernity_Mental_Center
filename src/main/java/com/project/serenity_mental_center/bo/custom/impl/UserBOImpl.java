package com.project.serenity_mental_center.bo.custom.impl;

import com.project.serenity_mental_center.bo.custom.UserBO;
import com.project.serenity_mental_center.dao.DAOFactory;
import com.project.serenity_mental_center.dao.custom.impl.PatientDAOImpl;
import com.project.serenity_mental_center.dao.custom.impl.UserDAOImpl;
import com.project.serenity_mental_center.dto.UserDto;
import com.project.serenity_mental_center.entity.User;

import java.util.ArrayList;
import java.util.Objects;

public class UserBOImpl implements UserBO {
    private UserDAOImpl userDAO = (UserDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);
    public String getPassword(String username) {
        return userDAO.getPassword(username);
    }

    public String getNextUserId() {
        String lastId = userDAO.getLastId();
        if (lastId != null) {
            String lastIndex = lastId.substring(1);
            int last = Integer.parseInt(lastIndex);
            int newIndex = last + 1;
            return String.format("U%03d", newIndex);
        }
        return "U001";
    }

    public boolean saveUser(UserDto userDto) {
        User user = new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getRole(),
                userDto.getEmail()
        );
        return userDAO.save(user);
    }

    public boolean getUserEmail(String emails) {
        String email = userDAO.checkMail(emails);
        if (email != null) {
            return email.equals(emails);
        }else {
            return false;
        }
    }

    public ArrayList<UserDto> getAllUsers() {
        ArrayList<User> users = userDAO.getAll();
        ArrayList<UserDto> userDtos = new ArrayList<>();
        for (User user: users){
            UserDto userDto = new UserDto(
                    user.getId(),
                    user.getName(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getRole(),
                    user.getEmail()
            );
            userDtos.add(userDto);
        }
        return userDtos;
    }

    public boolean deleteUser(String userId) {
        return userDAO.delete(userId);
    }

    public UserDto getAllByUserName(String userName, String password) {
        User user = userDAO.getAllByUserCredential(userName,password);
        if (user == null){
            return null;
        }
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getEmail()
        );
    }

    public boolean update(UserDto userDto, String newPwd) {
        User user = new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getUsername(),
                newPwd,
                userDto.getRole(),
                userDto.getEmail()
        );
        return userDAO.update(user);
    }
}
