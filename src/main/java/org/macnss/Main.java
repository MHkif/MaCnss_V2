package org.macnss;

import org.macnss.Controllers.Navigator;
import org.macnss.Utils.Session;

import java.text.ParseException;



public class Main {
    public  static Session SESSION = Session.getInstance();

    public static void main(String[] args) throws ParseException {
         Navigator.INSTANCE().index();
    }
}