package org.macnss.DAO;

import org.macnss.Entities.Company;

import java.sql.SQLException;

public interface ICompanyDAO extends DAO<Company>{
    final String TABLE = "companies";

    final String ID = "id";
    final String NAME = "name";
    final String EMAIL = "email";
    final String PASSWORD = "password";

    final String PRIMARY_KEY  = ID;

    final String TABLE_COLUMNS[] = {
            NAME,
            EMAIL,
            PASSWORD
    };

    final String COLUMNS = String.join(", ", TABLE_COLUMNS);
    final String HOLDERS = ",?".repeat(TABLE_COLUMNS.length + 1).replaceFirst(",", "");
    final String UPDATEHOLDERS = String.join("= ? ,", TABLE_COLUMNS).replaceAll("(,)$", "");

    public Company login(String email, String password) throws SQLException;


}
