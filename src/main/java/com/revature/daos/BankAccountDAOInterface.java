package com.revature.daos;

import com.revature.models.BankAccount;

import java.util.ArrayList;

public interface BankAccountDAOInterface {

    //Below are some abstract methods that BanKAccountDAO will implement

    //A method to Select all bankaccounts
    ArrayList<BankAccount> getAllBankAccounts();

    //A method to INSERT A NEW BANKACCOUNT

    BankAccount insertBankAccount(BankAccount bankAccount);

    boolean deleteBankAccount( int id);


}
