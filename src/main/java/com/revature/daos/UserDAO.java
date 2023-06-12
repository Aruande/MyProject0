package com.revature.daos;


import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//This Class is responsible for all things User DATA. (UserDAO == User Data Access Object)
//This Class will access/query the users table in the DB.
public class UserDAO implements UserDAOInterface {


    @Override
    public List<User> getAllUsers() {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM users";

            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();

            while (rs.next()) {

                //Extracts User data from RS by using rs.get_() methods. It's just an all args constructor to store all the data
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("address")
                );
                users.add(user);

            }
            return users;




        }catch (SQLException e){
            System.out.println("Error getting User!");
            e.printStackTrace(); //prints error message in the console, very important for debugging


        }
        return null;
    }

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
    public boolean updateUserAddress(User user) {

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "UPDATE users SET address = ? WHERE user_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);


            ps.setString(1 , user.getAddress());
            ps.setInt(2 , user.getUser_id());

            ps.executeUpdate();

            return true;
        }catch (SQLException e){
            System.out.println("Update failed");
            e.printStackTrace();
        }
        return false;
    }
}
