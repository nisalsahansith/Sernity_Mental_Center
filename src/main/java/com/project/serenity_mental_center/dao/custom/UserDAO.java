package com.project.serenity_mental_center.dao.custom;

import com.project.serenity_mental_center.dao.CrudDAO;
import com.project.serenity_mental_center.entity.User;

public interface UserDAO extends CrudDAO<User> {
    public String getPassword(String userName);
    public String checkMail(String emails);
    public User getAllByUserCredential(String userName, String password);
}
