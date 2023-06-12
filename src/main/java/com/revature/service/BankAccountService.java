package com.revature.service;

import com.revature.daos.BankAccountDAO;
import com.revature.daos.BankAccountDAOInterface;
import com.revature.models.BankAccount;

import java.util.ArrayList;

public class BankAccountService {

    private final BankAccountDAOInterface bankaccountDAO = new BankAccountDAO();

    public ArrayList<BankAccount> getAllBankAccounts(){
        return bankaccountDAO.getAllBankAccounts();
    }

    public BankAccount createNewBankAccount(BankAccount bankAccount){
        return bankaccountDAO.insertBankAccount(bankAccount);
    }

}
