package org.macnss.Services;

import org.macnss.dao.impl.AgentDAO;
import org.macnss.entity.Agent;

import java.util.List;

public class AgentService implements Service<Agent>{

   private final AgentDAO DAO = new AgentDAO();

    public Agent login(String email, String password) {
        Agent agent = DAO.login(email, password);
        if(agent != null){
            return agent;
        }else{
            return null;
        }
    }

    @Override
    public Agent save(Agent agent){
        if(DAO.save(agent) != null){
            return agent;
        }else {
            return null;
        }
    }

    @Override
    public Agent update(Agent agent){
        if(DAO.update(agent) != null){
            return agent;
        }else {
            return null;
        }
    }

    @Override
    public Agent findBy(String agentId){
        if(DAO.findBy(agentId) != null){
            return DAO.findBy(agentId);
        }else {
            return null;
        }

    }

    @Override
    public List<Agent> getAll(){
        List<Agent> agents = DAO.getAll();
        return agents;
    }

    @Override
    public boolean deactivate(String agentId){
        return DAO.deactivate(agentId);
    }

}
