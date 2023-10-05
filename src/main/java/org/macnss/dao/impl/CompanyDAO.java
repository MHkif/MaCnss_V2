package org.macnss.dao.impl;

import org.macnss.dao.ICompanyDAO;
import org.macnss.entity.Agent;
import org.macnss.entity.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO implements ICompanyDAO {
    @Override
    public Company save(Company company){
        String sql = "INSERT INTO "+ TABLE +"("+ PRIMARY_KEY +" "+COLUMNS+") VALUES("+ HOLDERS +") ";

        try(PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql);){
            preparedStatement.setString(1, company.getId());
            preparedStatement.setString(2, company.getName());
            preparedStatement.setString(3, company.getEmail());
            preparedStatement.setString(4, company.getPassword());

            if(preparedStatement.executeUpdate() > 0){
                return company;
            }else {

                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Company update(Company company) {
        String sql = "UPDATE "+ TABLE +" SET "+UPDATEHOLDERS+" WHERE "+ PRIMARY_KEY +" = ?";

        try(PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql);){
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getEmail());
            preparedStatement.setString(3, company.getPassword());
            preparedStatement.setString(4, company.getId());

            if(preparedStatement.executeUpdate() > 0){
                System.out.println("Employer has been updated successfully .");
                return company;
            }else {
                System.out.println("Update of Employer has been Failed");
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deactivate(String slag) {
        return false;
    }

    @Override
    public Company findBy(String id) {
        Company company = new Company();
        String sql = "SELECT * FROM "+TABLE+" WHERE "+PRIMARY_KEY+" = ?";

        try{
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet res = preparedStatement.executeQuery();

            if (res.next()){
                company.setId(res.getString(ID));
                company.setName(res.getString(NAME));
                company.setEmail(res.getString(EMAIL));
                company.setPassword(res.getString(PASSWORD));
            }else {
                return  null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return company;
    }

    @Override
    public List<Company> getAll(){
        List<Company> companies = new ArrayList<>();
        Company company = new Company();
        String sql = "SELECT * FROM "+TABLE;

        try{
            Statement statement = CONNECTION.createStatement();
            ResultSet res = statement.executeQuery(sql);

            if (res.next()){
                company.setId(res.getString(ID));
                company.setName(res.getString(NAME));
                company.setEmail(res.getString(EMAIL));
                company.setPassword(res.getString(PASSWORD));
                companies.add(company);
            }else {
                return  null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return companies;
    }


    @Override
    public Company login(String email, String password) {

        Company company = new Company();
        String sql = "SELECT * FROM "+TABLE+" WHERE email = ? AND password = ? ";
        try{
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet res = preparedStatement.executeQuery();

            if (res.next()){
                company.setId(res.getString(ID));
                company.setName(res.getString(NAME));
                company.setEmail(res.getString(EMAIL));
                company.setPassword(res.getString(PASSWORD));
            }else {
                return  null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return company;
    }
}
