package controllers;

import models.User;

import javax.jws.soap.SOAPBinding;

/**
 * Created by Jhon on 10/7/2017.
 */
public class UserDao extends DbManager<User,Integer> {

    private UserDao() {
        super(User.class);
    }

    private static UserDao instance;

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }
}
