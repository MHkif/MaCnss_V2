package org.macnss.dao.impl;

import org.macnss.dao.IAgentDAO;
import org.macnss.entity.Agent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AgentDAO implements IAgentDAO {

    @Override
    public Agent login(String emailP, String passwordP) {
        Agent agent = new Agent();
        String sql = "SELECT * FROM "+TABLE+" WHERE email = ? AND password = ? ";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, emailP);
            preparedStatement.setString(2, passwordP);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()){
                agent.setId(res.getString(ID));
                agent.setName(res.getString(NAME));
                agent.setEmail(res.getString(EMAIL));
                agent.setPassword(res.getString(PASSWORD));

            }else {
                return null;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  agent;
    }
    @Override
    public Agent insert(Agent agent)  {
        String sql = "INSERT INTO agents(id, name, email, password, verificationCode)  VALUES(?, ?, ?,?,?) ";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, agent.getId());
            preparedStatement.setString(2, agent.getName());
            preparedStatement.setString(3, agent.getEmail());
            preparedStatement.setString(4, agent.getPassword());

            if(preparedStatement.executeUpdate() > 0){

                return agent;
            }else {

                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Agent get(String agentId) {
        Agent agent = new Agent();
        String sql = "SELECT * FROM `agents` WHERE id = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, agentId);
            ResultSet res = preparedStatement.executeQuery();

            if (res.next()){
                agent.setId(res.getString(ID));
                agent.setName(res.getString(NAME));
                agent.setEmail(res.getString(EMAIL));
                agent.setPassword(res.getString(PASSWORD));
            }else {
                return  null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return agent;
    }

    @Override
    public List<Agent> getAll() {
        List<Agent> agents = new ArrayList<Agent>();
        Agent agent = new Agent();
        String sql = "SELECT * FROM `Agent` ;";

        try{
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);

            while (res.next()){
                agent.setId(res.getString(ID));
                agent.setName(res.getString(NAME));
                agent.setEmail(res.getString(EMAIL));
                agent.setPassword(res.getString(PASSWORD));
                agents.add(agent);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return agents;
    }

    @Override
    public Agent update(Agent agent) {
        String sql = "UPDATE agents SET name = ?, email = ?, password = ? WHERE id = ?";

        try( PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, agent.getName());
            preparedStatement.setString(2, agent.getEmail());
            preparedStatement.setString(3, agent.getPassword());
            preparedStatement.setString(4, agent.getId());

            if(preparedStatement.executeUpdate() > 0){
                System.out.println("Agent has been updated successfully .");
                return agent;
            }else {
                System.out.println("Update of Agent has been Failed");

                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String agentId) {
        String sql = "DELETE FROM agents WHERE id = ? ;";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, agentId);

            if(preparedStatement.executeUpdate() > 0){

                return true;
                }else {
                    return false;
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
