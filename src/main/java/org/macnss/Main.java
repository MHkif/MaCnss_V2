package org.macnss;

import org.macnss.controllers.Navigator;
import org.macnss.Utils.Session;
import org.macnss.dao.impl.EmployerDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Main {
    public  static Session SESSION = Session.getInstance();

    public static void main(String[] args) {
        Navigator.INSTANCE().index();
    }
}