package com.revature.controller;

import com.revature.models.BankAccount;
import com.revature.service.BankAccountService;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;

public class BankAccountController {
    private  static final BankAccountService bankAccountservice = new BankAccountService();

    private static final Logger logger = LoggerFactory.getLogger(BankAccountController.class);

    public static void handleGetAll(Context ctx) {
        ArrayList<BankAccount> bankAccounts = bankAccountservice.getAllBankAccounts();

        ctx.status(200);
        ctx.json(bankAccounts);

    }

    public  static void handleCreate(Context ctx){
        BankAccount bankAccount = ctx.bodyAsClass(BankAccount.class);

        BankAccount returnedBankAccount = bankAccountservice.createNewBankAccount(bankAccount);

        if (returnedBankAccount != null) {
            ctx.status(201);
            ctx.json(returnedBankAccount);
            logger.info("The following bankaccount was created: " + returnedBankAccount.toString());

        }else {
            ctx.status(400);
            logger.warn("Creation failed");
        }

    }
    public static void handleGetOne(Context ctx){
        ctx.status(405);
    }

    public static void handleUpdate(Context ctx){
        ctx.status(405);
    }

}
