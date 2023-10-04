package org.macnss.dao;

import org.macnss.entity.Agent;

import java.sql.SQLException;

public interface IAgentDAO extends DAO<Agent>{
    final String TABLE = "agents";
    final String ID = "id";
    final String NAME = "name";
    final String EMAIL = "email";
    final String PASSWORD = "password";

    public Agent login(String email, String password) throws SQLException;
}
