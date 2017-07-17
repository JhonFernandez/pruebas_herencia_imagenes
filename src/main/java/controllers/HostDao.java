package controllers;

import models.Host;

/**
 * Created by Jhon on 3/7/2017.
 */

public class HostDao extends DbManager<Host,Integer> {

    private HostDao() {
        super(Host.class);
    }

    private static HostDao instance;

    public static HostDao getInstance() {
        if (instance == null) {
            instance = new HostDao();
        }
        return instance;
    }
}