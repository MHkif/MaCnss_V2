package org.macnss.dao.impl;

import org.macnss.Enum.EmployerStatus;
import org.macnss.Enum.FolderStatus;
import org.macnss.dao.IEmployerDAO;
import org.macnss.entity.Employer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployerDAO implements IEmployerDAO {

    @Override
    public Employer save(Employer employer)  {
        String sql = "INSERT INTO "+ TABLE +"("+ MATRICULATE_COL+" , "+COLUMNS+") VALUES("+ HOLDERS +") ";

        try(PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql);){
            preparedStatement.setString(1, employer.getMatriculate());
            preparedStatement.setString(2, employer.getFirstName());
            preparedStatement.setString(3, employer.getLastName());
            preparedStatement.setDate(4, (Date) employer.getBirthDay());
            preparedStatement.setString(5, employer.getSalary());
            preparedStatement.setString(6, String.valueOf(employer.getStatus()));

            if(preparedStatement.executeUpdate() > 0){
                return employer;
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Employer get(String matriculate) {
        Employer employer = new Employer();
        String sql = "SELECT * FROM "+ TABLE +" WHERE "+ PRIMARY_KEY +" = ?";

        try{
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, matriculate);
            ResultSet res = preparedStatement.executeQuery();

            if (res.next()){
                employer.setMatriculate(res.getString(MATRICULATE_COL));
                employer.setFirstName(res.getString(FIRST_NAME));
                employer.setLastName(res.getString(LAST_NAME));
                employer.setBirthDay(res.getDate(BIRTH_DAY));
                employer.setSalary(res.getString(SALARY));
                employer.setStatus(EmployerStatus.valueOf(res.getString(STATUS)));
            }else {
                return  null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employer;
    }

    @Override
    public List<Employer> getAll() {
        List<Employer> employers = new ArrayList<Employer>();
        Employer employer = new Employer();
        String sql = "SELECT * FROM "+ TABLE;
        try{
            Statement statement = CONNECTION.createStatement();
            ResultSet res = statement.executeQuery(sql);

            while (res.next()){
                employer.setMatriculate(res.getString(MATRICULATE_COL));
                employer.setFirstName(res.getString(FIRST_NAME));
                employer.setLastName(res.getString(LAST_NAME));
                employer.setBirthDay(res.getDate(BIRTH_DAY));
                employer.setSalary(res.getString(SALARY));
                employer.setStatus(EmployerStatus.valueOf(res.getString(STATUS)));
                employers.add(employer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employers;
    }

    @Override
    public Employer update(Employer employer) {
        String sql = "UPDATE "+ TABLE +" SET "+UPDATEHOLDERS+" WHERE "+ PRIMARY_KEY +" = ?";

        try(PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql);){
            // # TODO : Need to Fix
            preparedStatement.setString(1, employer.getFirstName());
            preparedStatement.setString(2, employer.getLastName());
            preparedStatement.setDate(3, (Date) employer.getBirthDay());
            preparedStatement.setString(4, employer.getSalary());
            preparedStatement.setString(5, employer.getMatriculate());

            if(preparedStatement.executeUpdate() > 0){
                System.out.println("Patient has been updated successfully .");
                return employer;
            }else {
                System.out.println("Update of Patient has been Failed");
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deactivate(String slag) throws SQLException {
        return false;
    }


    @Override
    public Employer login(String email, String password) throws SQLException {

        Employer employer = new Employer();
        String sql = "SELECT * FROM "+TABLE+" WHERE email = ? AND password = ? ";
        try{
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet res = preparedStatement.executeQuery();

            if (res.next()){
                employer.setMatriculate(res.getString(MATRICULATE_COL));
                employer.setFirstName(res.getString(FIRST_NAME));
                employer.setLastName(res.getString(LAST_NAME));
                employer.setBirthDay(res.getDate(BIRTH_DAY));
                employer.setSalary(res.getString(SALARY));
            }else {
                return  null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employer;
    }


}
