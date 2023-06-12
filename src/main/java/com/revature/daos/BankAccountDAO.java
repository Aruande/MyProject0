package com.revature.daos;

import com.revature.models.BankAccount;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class BankAccountDAO implements BankAccountDAOInterface {


    @Override
    public ArrayList<BankAccount> getAllBankAccounts() {

        //instantiate a connection object to talk to DB
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM bankaccounts";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<BankAccount> bankAccountArrayList = new ArrayList<>();//instantiate empty arraylist that holds incoming data

            //Instantiate a UserDao to get User object
            UserDAO uDAO = new UserDAO();

            while (rs.next()){
                BankAccount bankAccount = new BankAccount(
                        rs.getInt("account_id"),
                        rs.getString("account_type"),
                        rs.getDouble("balance"),
                        uDAO.getUserById(rs.getInt("user_id_fk"))

                );
            }

        }catch (SQLException e){
            System.out.println("Failed to get all bankaccounts");
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public BankAccount insertBankAccount(BankAccount bankAccount) {

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "INSERT INTO bankaccounts (account_type, balance, user_id_fk) VALUES (?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, bankAccount.getAccount_type());
            ps.setDouble(2, bankAccount.getBalance());
            ps.setInt(3, bankAccount.getUser_id_fk());

            ps.executeUpdate();

            return bankAccount;



        }catch (SQLException e){
            System.out.println("Insert bankaccount failed!");
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteBankAccount(int id) {
        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "DELETE FROM bankaccounts WHERE account_id = ?";

            PreparedStatement ps =conn.prepareStatement(sql);

            ps.setInt(1,id);

            return ps.executeUpdate() == 1;

        }catch (SQLException e){
            System.out.println("Delete bankaccount failed!");
            e.printStackTrace();

        }
        return false;
    }
}
