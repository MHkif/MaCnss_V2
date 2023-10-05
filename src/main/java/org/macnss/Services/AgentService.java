package org.macnss.Services;

import org.macnss.dao.impl.AgentDAO;
import org.macnss.entity.Agent;

import java.util.List;

public class AgentService implements Service<Agent>{

   private final AgentDAO agentDAO = new AgentDAO();

    public Agent login(String email, String password) {
        Agent agent = agentDAO.login(email, password);
        if(agent != null){
            return agent;
        }else{
            return null;
        }
    }

    @Override
    public Agent save(Agent agent){
        if(agentDAO.save(agent) != null){
            return agent;
        }else {
            return null;
        }
    }

    @Override
    public Agent update(Agent agent){
        if(agentDAO.update(agent) != null){
            return agent;
        }else {
            return null;
        }
    }

    @Override
    public Agent findBy(String agentId){
        if(agentDAO.findBy(agentId) != null){
            return agentDAO.findBy(agentId);
        }else {
            return null;
        }

    }

    @Override
    public List<Agent> getAll(){
        List<Agent> agents = agentDAO.getAll();
        return agents;
    }

    @Override
    public boolean deactivate(String agentId){
        return agentDAO.deactivate(agentId);
    }

}
