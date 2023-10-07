package org.macnss.dao.impl;

import org.macnss.Enum.EmployerStatus;
import org.macnss.Enum.FolderStatus;
import org.macnss.Main;
import org.macnss.dao.IEmployerDAO;
import org.macnss.entity.Company;
import org.macnss.entity.Employer;

import javax.xml.transform.sax.SAXSource;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class EmployerDAO implements IEmployerDAO {

    @Override
    public Employer save(Employer employer)  {
        String sql = "INSERT INTO "+ TABLE +"("+ MATRICULATE_COL+" , "+COLUMNS+") VALUES("+ HOLDERS +") ";

        try(PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql);){
            preparedStatement.setString(1, employer.getMatriculate());
            preparedStatement.setString(2, employer.getFirstName());
            preparedStatement.setString(3, employer.getLastName());
            preparedStatement.setString(4, employer.getEmail());
            preparedStatement.setString(5, employer.getPassword());
            preparedStatement.setDate(6, (Date) employer.getBirthDay());
            preparedStatement.setString(7,  employer.getSalary());
            preparedStatement.setString(8, String.valueOf(employer.getStatus()));

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
    public Employer findBy(String matriculate) {
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
                employer.setEmail(res.getString(EMAIL));
                employer.setPassword(res.getString(PASSWORD));
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
                employer.setEmail(res.getString(EMAIL));
                employer.setPassword(res.getString(PASSWORD));
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
                System.out.println("Employer has been updated successfully .");
                return employer;
            }else {
                System.out.println("Update of Employer has been Failed");
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
    public Employer login(String email, String password){

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


    public double retirementSalary(Employer employer){

        double retirementSalary = Double.parseDouble(employer.getSalary()) * 0.5;

        int workedDays = workedDays(employer);

        if(workedDays < 1320){
            return 0;
        }
       else if (workedDays <= 3240)
       {
            if( retirementSalary > 6000){
                return  6000;
            }
       }
       else
       {
           int baseSalaryIncrementRate = (workedDays - 3240) / 216;
           if(baseSalaryIncrementRate > 70){
               baseSalaryIncrementRate = 70;
               }

           retirementSalary += ((retirementSalary * 0.01) * baseSalaryIncrementRate);

           if(retirementSalary > 6000) {
               return 6000;
           }
        }
        if(retirementSalary < 1000){
            return 1000;
        }else{
            return retirementSalary;
        }
    }

    public boolean associateEmployerWithCompany(Employer employer, Company company, int days, double salary) {
        String sql = "INSERT INTO employerhistory(em_matriculate, company_id, days, date, salary)" +
                " VALUES(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql);) {
            preparedStatement.setString(1, employer.getMatriculate());
            preparedStatement.setString(2, company.getId());
            preparedStatement.setInt(3, days);
            preparedStatement.setDouble(4, salary);


            if(preparedStatement.executeUpdate() > 0){
                employer.setSalary(String.valueOf(salary));
                update(employer);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public int workedDays(Employer employer) {
        String sql = "SELECT SUM(days) AS 'workedDays' FROM employerHistory WHERE  em_matriculate = ?";
        int days = 0;
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql);) {
            preparedStatement.setString(1, employer.getMatriculate());
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                days = res.getInt("workedDays");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return days;
    }


    public void insert() throws ParseException {
        String[] salaryHistory = {
                "8000.00",
                "11000.00",
                "12000.00",
                "14000.00",
                "17000.00",
                "20000.00",
                "22000.00",
                "24000.00",
        };

        String sql = "INSERT INTO employerhistory(em_matriculate, company_id, days, date, salary)" +
                " VALUES(?,?,?,?,?)";
        Employer employer = findBy("MgBolNTJ");

        Calendar calendar = Calendar.getInstance();

        int currentYear = calendar.get(Calendar.YEAR) - 7;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM", Locale.FRANCE);

        try(PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql);){

            for (int i = 0; i < 8; i++) {
                int year = currentYear + i;

                for (int month = 1; month <= 12; month++) {
                    double salary = Double.parseDouble(salaryHistory[i]) + 2000;
                    java.util.Date date =   formatter.parse(String.format("%04d-%02d", year, month));

                    if(month > 6) salary += 2000.00;

                    preparedStatement.setString(1, employer.getMatriculate());
                    preparedStatement.setString(2, "HoiTlm86g");
                    preparedStatement.setInt(3, 26);
                    preparedStatement.setDate(4, new Date(date.getTime()) );
                    preparedStatement.setString(5, String.valueOf(salary));
                    preparedStatement.addBatch();
                }

            }
            int[] affectedRows = preparedStatement.executeBatch();
            System.out.println("Inserted " + affectedRows.length + " rows for salary history.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
