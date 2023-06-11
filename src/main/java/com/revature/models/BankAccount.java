package com.revature.models;

public class BankAccount {

    private int account_id;
    private String account_type;
    private double balance;
    // User objects in java will contain entire User instead of just the foreign key.
    // This has all of the relevant data to the User

    private User user;

    // this variable makes inserts easier (when I insert a new Bank Account instead of a entire user
    private int user_id_fk;

    //constructors---

    //no args
    public BankAccount() {
    }

    //all args (with User object)
    public BankAccount(int account_id, String account_type, double balance, User user) {
        this.account_id = account_id;
        this.account_type = account_type;
        this.balance = balance;
        this.user = user;
    }

    //all args minus id (with User Object)
    public BankAccount(String account_type, double balance, User user) {
        this.account_type = account_type;
        this.balance = balance;
        this.user = user;
    }

    // all args minus id (w/ user_id_fk) ***ONLY used for INSERTS
    public BankAccount(String account_type, double balance, int user_id_fk) {
        this.account_type = account_type;
        this.balance = balance;
        this.user_id_fk = user_id_fk;
    }

    //getters and setters

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUser_id_fk() {
        return user_id_fk;
    }

    public void setUser_id_fk(int user_id_fk) {
        this.user_id_fk = user_id_fk;
    }
    //toString() allows me to print out my BankAccount objects
    @Override
    public String toString() {
        return "BankAccount{" +
                "account_id=" + account_id +
                ", account_type='" + account_type + '\'' +
                ", balance=" + balance +
                ", user=" + user +
                ", user_id_fk=" + user_id_fk +
                '}';
    }
}
