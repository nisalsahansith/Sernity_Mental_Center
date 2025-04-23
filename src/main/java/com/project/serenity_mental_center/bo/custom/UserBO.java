package com.project.serenity_mental_center.bo.custom;

import com.project.serenity_mental_center.bo.SuperBO;
import com.project.serenity_mental_center.dto.UserDto;
import com.project.serenity_mental_center.entity.User;

import java.util.ArrayList;

public interface UserBO extends SuperBO {
    public String getPassword(String username);
    public String getNextUserId();
    public boolean saveUser(UserDto userDto);
    public boolean getUserEmail(String emails);
    public ArrayList<UserDto> getAllUsers();
    public boolean deleteUser(String userId);
    public UserDto getAllByUserName(String userName, String password);
    public boolean update(UserDto userDto, String newPwd);
}
