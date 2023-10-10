package org.macnss.Services;

import org.macnss.DAO.impl.AdminDAO;
import org.macnss.Entities.Admin;

import java.sql.SQLException;

public class AdminService {
    private final AdminDAO adminDAO = new AdminDAO();

    public Admin login(String email, String password) throws SQLException {
      Admin admin = adminDAO.login(email, password);
      if(admin != null){
          return admin;
      }else{
          return null;
      }

    }

}
