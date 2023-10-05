package org.macnss;

import org.macnss.controllers.Navigator;
import org.macnss.Utils.Session;


public class Main {
    public  static Session SESSION = Session.getInstance();
    public static void main(String[] args) {
        Navigator.INSTANCE().index();
    }
}