package com.revature;

import com.revature.daos.BankAccountDAO;
import com.revature.daos.UserDAO;
import com.revature.models.BankAccount;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.JavalinAppConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class Driver {

    public static void main(String[] args) {

        try(Connection conn = ConnectionUtil.getConnection()) {
            System.out.println("Connection Successful");
        }catch (SQLException e) {
            System.out.println("Connection Failed");
        }
        //Calling Dao methods below-----
        //instantiate a UserDao object to use its methods

        UserDAO uDAO = new UserDAO();

        // Getting a User object by id
        System.out.println(uDAO.getUserById(3));

        // update address for user
       // System.out.println(uDAO.updateUserAddress("343 Willow st", "Sarah Dog"));
        System.out.println(uDAO.getUserById(2));

        //Instantiate BankAccountDAO

        BankAccountDAO bDAO = new BankAccountDAO();

        BankAccount newAccount = new BankAccount("Savings", 5000.00,2);



        // Create a new instance of the Javalin Config class
        JavalinAppConfig app = new JavalinAppConfig();

        // Start the app with app.start
        app.start(7070);








    }
}
