package org.macnss.dao.impl;

import org.macnss.dao.IPatient;
import org.macnss.entity.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO implements IPatient {

    @Override
    public Patient insert(Patient patient)  {
        String sql = "INSERT INTO "+ TABLE +"(matriculate, name)  VALUES(?,?) ";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, patient.getMatriculate());
            preparedStatement.setString(2, patient.getFullName());

            if(preparedStatement.executeUpdate() > 0){
                return patient;
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Patient get(String matriculate) {
        Patient patient = new Patient();
        String sql = "SELECT * FROM "+ TABLE +" WHERE matriculate = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, matriculate);
            ResultSet res = preparedStatement.executeQuery();

            if (res.next()){
                patient.setMatricule(res.getString(MATRICULATE_COL));
                patient.setFullName(res.getString(FULL_NAME));
            }else {
                return  null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return patient;
    }

    @Override
    public List<Patient> getAll() {
        List<Patient> patients = new ArrayList<Patient>();
        Patient patient = new Patient();
        String sql = "SELECT * FROM "+ TABLE;
        try{
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);

            while (res.next()){
                patient.setMatricule(res.getString(MATRICULATE_COL));
                patient.setFullName(res.getString(FULL_NAME));
                patients.add(patient);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patients;
    }

    @Override
    public Patient update(Patient patient) {
        String sql = "UPDATE "+ TABLE +" SET fullName = ? WHERE matriculate = ?";

        try( PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, patient.getFullName());
            preparedStatement.setString(4, patient.getMatriculate());

            if(preparedStatement.executeUpdate() > 0){
                System.out.println("Patient has been updated successfully .");
                return patient;
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
