package com.revature.service;

import com.revature.daos.BankAccountDAO;
import com.revature.daos.BankAccountDAOInterface;
import com.revature.models.BankAccount;

import java.util.ArrayList;

public class BankAccountService {

    private final BankAccountDAOInterface bankAccountDAO = new BankAccountDAO();

    public ArrayList<BankAccount> getAllBankAccounts(){
        return bankAccountDAO.getAllBankAccounts();
    }

    public BankAccount createNewBankAccount(BankAccount bankAccount){
        return bankAccountDAO.insertBankAccount(bankAccount);
    }

    public boolean deleteBankAccount(int id){
        return bankAccountDAO.deleteBankAccount(id);
    }

}
