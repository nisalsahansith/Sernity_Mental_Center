package com.project.serenity_mental_center.bo.custom.impl;

import com.project.serenity_mental_center.dao.custom.impl.UserDAOImpl;

public class UserBOImpl {
    private UserDAOImpl userDAO = new UserDAOImpl();
    public String getPassword(String username) {
        return userDAO.getPassword(username);
    }
}
