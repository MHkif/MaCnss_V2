package org.macnss.Services;

import org.macnss.dao.impl.PatientDAO;
import org.macnss.entity.Patient;

import java.util.List;

public class PatientService{

    private final PatientDAO Dao = new PatientDAO();

    public Patient create(Patient patient){
        if (Dao.insert(patient) != null){
            return patient;
        }else {
            return null;
        }
    }

    public List<Patient> getAll() {
        List<Patient> patients = Dao.getAll();
        return patients;
    }
    public Patient get(String matriculate) {
        if (Dao.get(matriculate) != null){
            return Dao.get(matriculate);
        }else  {
            return  null;
        }
    }
}
