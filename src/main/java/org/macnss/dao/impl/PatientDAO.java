package org.macnss.dao.impl;

import org.macnss.dao.IPatient;
import org.macnss.entity.Employer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO implements IPatient {

    @Override
    public Employer insert(Employer employer)  {
        String sql = "INSERT INTO "+ TABLE +"(matriculate, name)  VALUES(?,?) ";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, employer.getMatriculate());
            preparedStatement.setString(2, employer.getFirstName());

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
        String sql = "SELECT * FROM "+ TABLE +" WHERE matriculate = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, matriculate);
            ResultSet res = preparedStatement.executeQuery();

            if (res.next()){
                employer.setMatriculate(res.getString(MATRICULATE_COL));
                employer.setFirstName(res.getString(FULL_NAME));
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
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);

            while (res.next()){
                employer.setMatriculate(res.getString(MATRICULATE_COL));
                employer.setFirstName(res.getString(FULL_NAME));
                employers.add(employer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employers;
    }

    @Override
    public Employer update(Employer employer) {
        String sql = "UPDATE "+ TABLE +" SET fullName = ? WHERE matriculate = ?";

        try( PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, employer.getFirstName());
            preparedStatement.setString(4, employer.getMatriculate());

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
    public boolean delete(String slag) throws SQLException {
        return false;
    }


}
