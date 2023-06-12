package com.revature.service;


import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOInterface;
import com.revature.models.User;

import java.util.List;

// Business logic layer for the application that is called in the controller layer and the dao layer to interact w/ the DB
public class UserService {
    //gets an instance of the UserDAO

    private static  UserDAOInterface userDAO;
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }




    //private final UserDAOInterface userDAO = new UserDAO();

    public static User getUserById(int id){

        if(id > 0){
            return  userDAO.getUserById(id);
        }else {
            return null;
        }

    }
    public static boolean updateUserAddress(User user) {
        return userDAO.updateUserAddress(user);
    }

}
