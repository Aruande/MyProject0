package com.revature.daos;


import com.revature.models.User;

//This Interface will lay out the methods that the RoleDAO Implements
//This is a great way to document what functionalities exist in the RoleDAO
public interface UserDAOInterface {

    // a method that selects a User by its ID
    User getUserById(int id); // int represent a user id


    boolean updateUserAddress(String username, String address);




}
