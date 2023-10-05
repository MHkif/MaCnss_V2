package org.macnss.Services;

import org.macnss.dao.impl.AdminDAO;
import org.macnss.dao.impl.AgentDAO;
import org.macnss.entity.Admin;
import org.macnss.entity.Agent;

import java.sql.SQLException;
import java.util.List;

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
