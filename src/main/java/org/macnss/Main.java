package org.macnss;

import org.macnss.controllers.AdminController;
import org.macnss.controllers.Navigator;
import org.macnss.helpers.PrintStatement;
import org.macnss.helpers.Session;
import org.macnss.helpers.Validator;

import java.rmi.server.UID;


public class Main {
    public  static Session SESSION = Session.getInstance();
    public static void main(String[] args) {
        Navigator.INSTANCE().index();
    }
}