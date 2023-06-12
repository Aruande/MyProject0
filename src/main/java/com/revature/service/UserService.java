package com.revature.service;


import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOInterface;
import com.revature.models.User;

// Business logic layer for the application that is called in the controller layer and the dao layer to interact w/ the DB
public class UserService {
    //gets an instance of the UserDAO

    private static  UserDAOInterface userDAO;
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    //private final UserDAOInterface userDAO = new UserDAO();

    public static User getUserById(int id){

        if(id > 0){
            return  userDAO.getUserById(id);
        }else {
            return null;
        }

    }
    public static boolean updateUserAddress(String address, String username) {
        if (username == null || username.trim().equals("")) {
            return false;
        }
        char[] usernameArray = username.toLowerCase().toCharArray();
        String formattedUsername = "";
        formattedUsername += Character.toUpperCase(usernameArray[0]);
        for (int i = 1; i < usernameArray.length; i++) {
            if (usernameArray[i - 1] == ' ') {
                formattedUsername += Character.toUpperCase(usernameArray[i]);
            } else {
                formattedUsername += usernameArray[i];
            }
        }
        if (address != null && !address.isEmpty()) {

            return userDAO.updateUserAddress(address, formattedUsername);
        }
        return false;
    }

}
