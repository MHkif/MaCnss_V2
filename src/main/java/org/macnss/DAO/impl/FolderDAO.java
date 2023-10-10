package org.macnss.DAO.impl;

import org.macnss.Enum.EmployerStatus;
import org.macnss.Enum.FolderStatus;
import org.macnss.DAO.IFolderDAO;
import org.macnss.Entities.Agent;
import org.macnss.Entities.Employer;
import org.macnss.Entities.Folder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FolderDAO implements IFolderDAO {

    @Override
    public Folder save(Folder folder) {
        String sql = "INSERT INTO "+TABLE+"(id, name, despositAt, status, matriculate, total_refund, agent_id) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1,folder.getId());
            preparedStatement.setString(2,folder.getName());
            preparedStatement.setDate(3, (Date) folder.getDepositDate());
            preparedStatement.setString(4, String.valueOf(folder.getStatus()));
            preparedStatement.setString(5,folder.getEmployer().getMatriculate());
            preparedStatement.setFloat(6,folder.getTotal_refund());
            preparedStatement.setString(7,folder.getAgent().getId());
            int row = preparedStatement.executeUpdate();
            if(row > 0){
                return folder;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Folder update(Folder folder) {
        String sql = "UPDATE "+TABLE+" SET status = ? where id = ?";
        try {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(folder.getStatus()));
            preparedStatement.setString(2,folder.getId());
            int row = preparedStatement.executeUpdate();
            if (row > 0){
                return folder;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deactivate(String slag)  {
        return false;
    }


    @Override
    public Folder findBy(String id) {
        String sql = """
                SELECT f.*, p.fullName AS p_fullName  , a.name AS a_name, 
                a.email AS a_email, a.password AS a_password
                FROM folders AS f
                INNER JOIN employers AS p ON f.matriculate = p.matriculate
                INNER JOIN agents AS a ON f.agent_id = a.id WHERE f.id = ?""";
        Folder folder = new Folder();
        try {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Employer employer = new Employer();
                Agent agent = new Agent();
                folder.setId(resultSet.getString(ID));
                folder.setName(resultSet.getString(NAME));
                folder.setDepositDate(resultSet.getDate(DEPOSIT_DATE));
                folder.setStatus(FolderStatus.valueOf(resultSet.getString(STATUS)));
                folder.setTotal_refund(resultSet.getFloat(TOTAL_REFUND));

                employer.setMatriculate(resultSet.getString("matriculate"));
                employer.setFirstName(resultSet.getString("p_fullName"));

                agent.setId(resultSet.getString("agent_id"));
                agent.setName(resultSet.getString("a_name"));
                agent.setEmail(resultSet.getString("a_email"));

                agent.setPassword(resultSet.getString("a_password"));
                folder.setEmployer(employer);
                folder.setAgent(agent);


            }
        }catch (SQLException e){
            new RuntimeException();
        }
        return folder;
    }


    @Override
    public List<Folder> getAll(){
        String sql = """
                SELECT f.*, e.*, a.*
                FROM folders AS f
                INNER JOIN employers AS e ON f.matriculate = e.matriculate
                INNER JOIN agents AS a ON f.agent_id = a.id """;
        List<Folder> folders = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Folder folder = new Folder();
                Employer employer = new Employer();
                Agent agent = new Agent();

                folder.setId(resultSet.getString(ID));
                folder.setName(resultSet.getString(NAME));
                folder.setDepositDate(resultSet.getDate(DEPOSIT_DATE));
                folder.setStatus(FolderStatus.valueOf(resultSet.getString(STATUS)));
                folder.setTotal_refund(resultSet.getFloat(TOTAL_REFUND));

                employer.setMatriculate(resultSet.getString("matriculate"));
                employer.setFirstName(resultSet.getString("firstName"));
                employer.setLastName(resultSet.getString("lastName"));
                employer.setEmail(resultSet.getString("e.email"));
                employer.setPassword(resultSet.getString("e.password"));
                employer.setBirthDay(resultSet.getDate("e.birthDay"));
                employer.setSalary(resultSet.getString("e.salary"));
                employer.setStatus(EmployerStatus.valueOf(resultSet.getString("e.status")));
                agent.setId(resultSet.getString("a.id"));
                agent.setName(resultSet.getString("a.name"));
                agent.setEmail(resultSet.getString("a.email"));
                agent.setPassword(resultSet.getString("a.password"));

                folder.setEmployer(employer);
                folder.setAgent(agent);
                folders.add(folder);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return folders;
    }
}
