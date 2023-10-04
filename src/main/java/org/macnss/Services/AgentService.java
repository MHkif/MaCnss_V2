package org.macnss.Services;

import org.macnss.dao.impl.AgentDAO;
import org.macnss.entity.Admin;
import org.macnss.entity.Agent;

import java.sql.SQLException;

public class AgentService {

    AgentDAO agentDAO = new AgentDAO();


    public Agent login(String email, String password) {
        Agent agent = agentDAO.login(email, password);
        if(agent != null){
            return agent;
        }else{
            return null;
        }
    }

}
