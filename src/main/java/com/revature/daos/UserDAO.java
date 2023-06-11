package com.revature.daos;


import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//This Class is responsible for all things User DATA. (UserDAO == User Data Access Object)
//This Class will access/query the users table in the DB.
public class UserDAO implements UserDAOInterface {


    @Override
    public User getUserById(int id) {
        //use a try-with-resources block to open the connection or our Database
        try(Connection conn = ConnectionUtil.getConnection()){
              /* a String that holds the SQL command I want to run on the DB.
             This String has a wildcard/parameter/variable for the user_id (the ?)
              to take the user-inputted user id and put it into this wildcard*/
            String sql = "SELECT * FROM users WHERE user_id = ?";

            //PreparedStatement object fills in the wildcard(?). prepares a sql command before sending to database

            PreparedStatement ps = conn.prepareStatement(sql);

            //inserting a value for the (?), fill in the id variable
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            // checks to see if there is any data in the RS that hasn't been accessed yet.
            if(rs.next()){

                //Extracts User data from RS by using rs.get_() methods. It's just an all args constructor to store all the data
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("address")
                );
                return user;// returns User object to user
            }

        }catch (SQLException e) {
            System.out.println("Error getting User!");
            e.printStackTrace(); //prints error message in the console, very important for debugging

        }



        return null;
    }

    @Override
    public boolean updateUserAddress( String address, String username) {

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "UPDATE users SET address = ? WHERE username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);


            ps.setString(1 , address);
            ps.setString(2 , username);

            ps.executeUpdate();

            return true;
        }catch (SQLException e){
            System.out.println("Update failed");
            e.printStackTrace();
        }
        return false;
    }
}
