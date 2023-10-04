package org.macnss.dao;

import org.macnss.entity.Patient;

public interface IPatient extends DAO<Patient> {

    final String TABLE = "patients";
    final String MATRICULATE_COL = "matriculate";
    final String FULL_NAME = "fullName";

}
