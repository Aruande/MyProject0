package com.revature.controller;

import com.revature.daos.UserDAO;
import com.revature.models.User;
import com.revature.service.UserService;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserController {

    private static final UserService userservice = new UserService(new UserDAO());

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public static void handleGetOne(Context ctx){
        // Recall that the path for this will be http://localhost:7070/roles/{id}
        // This will match http://localhost:7070/users/1
        // But it will also match http://localhost:7070/users/NaN

        //int x = ctx.pathParam("id"); // We need to find a way to parse this
        int id;
        try{
            id = Integer.parseInt(ctx.pathParam("id"));

        }catch (NumberFormatException e){
            // This block running means they didn't have a valid integer in their path
            ctx.status(400);

            // Let's add a logger to show the invalid id
            logger.warn("Unable to parse id = " + ctx.pathParam("id"));

            // Adding a return statement here because there's no point continuing with a bad int
            return;
        }

        // Let's call the role service and attempt to pull the value
        User user = UserService.getUserById(id);

        // We need to check if the role is null or not
        if (user != null){
            // This is good, it found the roll
            ctx.status(200);
            ctx.json(user);
            // This is unnecessary but we'll add a log here
            logger.info("The following user was obtained from db: " + user.toString());
        } else{
            ctx.status(404);
            logger.warn("No resource was found at id = " + id + " from ip: " + ctx.ip());
        }
    }

    public static void handleGetAll(Context ctx){
        ctx.json(userservice.getAllUsers());
    }

    public static void handleCreate(Context ctx){
        ctx.json(userservice.getAllUsers());

    }

    public static void handleUpdate(Context ctx){
        // We'll start here for simplicity
        // We expect a role title and a role salary to come in from the client

        // We need to deserialize that and create a Role object
        User submittedUser = ctx.bodyAsClass(User.class);

        // Call the roleService to actually do something with this info
        boolean updateSuccessful = UserService.updateUserAddress(submittedUser);

        // So updateSuccessful should let us know if we successfully updated the DB
        if (updateSuccessful){
            // This is good
            ctx.status(200);
            // Successful update should have some logging
            logger.info("User: " + submittedUser.getUsername() + " was updated to new address: " +
                    submittedUser.getUsername());
        } else{
            // Was not able to update DB for some reason
            ctx.status(400);
        }
    }

    public static void handleDelete(Context ctx){
        ctx.status(405); // Method is not allowed
    }


}
