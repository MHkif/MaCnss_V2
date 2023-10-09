package org.macnss.dao;

import org.macnss.entity.Company;
import org.macnss.entity.Employer;

import java.sql.SQLException;

public interface IEmployerDAO extends DAO<Employer> {

    final String TABLE = "employers";
    final String MATRICULATE_COL = "matriculate";
    final String FIRST_NAME = "firstName";
    final String LAST_NAME = "lastName";
    final String EMAIL = "email";
    final String PASSWORD = "password";
    final String BIRTH_DAY = "birthDay";
    final String SALARY = "salary";
    final String STATUS = "status";

    final String PRIMARY_KEY  = MATRICULATE_COL;

    final String[] TABLE_COLUMNS = {
            FIRST_NAME,
            LAST_NAME,
            EMAIL,
            PASSWORD,
            BIRTH_DAY,
            SALARY,
            STATUS
    };

   final String COLUMNS = String.join(", ", TABLE_COLUMNS);
   final String HOLDERS = ",?".repeat(TABLE_COLUMNS.length +1).replaceFirst(",", "");
   final String UPDATEHOLDERS = String.join(" = ? ,", TABLE_COLUMNS).replaceAll("()$", " = ? ");

    public Employer login(String email, String password) throws SQLException;

}
