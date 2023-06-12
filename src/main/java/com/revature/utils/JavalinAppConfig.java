package com.revature.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import com.revature.controller.BankAccountController;
import com.revature.controller.UserController;
import io.javalin.json.JsonMapper;;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinAppConfig {

    //
    Gson gson = new GsonBuilder().create();

    JsonMapper gsonMapper = new JsonMapper() {
        @Override
        public String toJsonString(@NotNull Object obj, @NotNull Type type) {
            return gson.toJson(obj, type);
        }


        @Override
        public <T> T fromJsonString(@NotNull String json, @NotNull Type targetType) {
            return gson.fromJson(json, targetType);
        }
    };


    private static final Logger logger = LoggerFactory.getLogger(JavalinAppConfig.class);






    private Javalin app = Javalin.create(config -> config.jsonMapper(gsonMapper))
            .before(ctx -> {
                logger.info(ctx.method() + " Request was sent to path: \" + ctx.fullUrl()); ");
            })

            .routes(() ->{
                // each path will allow to group like method
                path("bankAccounts", () ->{
                    // Declare my routes and methods super quickly
                    get(BankAccountController::handleGetAll);
                    // app.get("/employees", EmployeeController::handleGetAll)
                    post(BankAccountController::handleCreate);
                    put(BankAccountController::handleUpdate);
                    //delete(BankAccountController::handleDelete);
                    // What about /employees/{id}?????
                    path("{id}", () ->{
                        get(BankAccountController::handleGetOne);
                    });
                });
                path("users", () ->{
                    // Declare my routes and methods super quickly
                    get(UserController::handleGetAll);
                    post(UserController::handleCreate);
                    put(UserController::handleUpdate);
                    delete(UserController::handleDelete);
                    // What about /users/{id}?????
                    path("{id}", () ->{
                        get(UserController::handleGetOne);
                    });
                });
            });

    // To make it so the main method of the Driver class is what STARTS the application, we'll create a method that
    // allows to start the javalin app we've configured here

    public void start(int port){
        app.start(port);
    }
        // routes will declare all our possible paths


}
