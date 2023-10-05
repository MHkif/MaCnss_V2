package org.macnss.dao;

import org.macnss.entity.Employer;

public interface IPatient extends DAO<Employer> {

    final String TABLE = "patients";
    final String MATRICULATE_COL = "matriculate";
    final String FULL_NAME = "fullName";

}
