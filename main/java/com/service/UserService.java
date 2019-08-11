package com.service;

import com.entity.User;
import com.util.Error;

import java.util.List;

public interface UserService {
    /**
     *Insert the manager
     * @param manager  the entity of User
     */
    Error addManager(User manager);

    /**
     *Delete the manager
     * @param userName  the name of user
     */
    Error deleteManager(String userName);

    /*Update the manager
     * @param manager  the entity of user
     */
    Error updateManager(User manager);

    /*
    *Select all managers
     */
    List<User> getAllManager();
}
