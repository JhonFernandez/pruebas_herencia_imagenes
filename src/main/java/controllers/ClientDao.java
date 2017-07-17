package controllers;

import models.Client;

/**
 * Created by Jhon on 3/7/2017.
 */

public class ClientDao extends DbManager<Client,String> {

    private ClientDao() {
        super(Client.class);
    }

    private static ClientDao instance;

    public static ClientDao getInstance() {
        if (instance == null) {
            instance = new ClientDao();
        }
        return instance;
    }
}