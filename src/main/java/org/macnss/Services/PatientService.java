package org.macnss.Services;

import org.macnss.dao.impl.EmployerDAO;
import org.macnss.entity.Employer;

import java.util.List;

public class PatientService{

    private final EmployerDAO Dao = new EmployerDAO();

    public Employer create(Employer employer){
        if (Dao.save(employer) != null){
            return employer;
        }else {
            return null;
        }
    }

    public List<Employer> getAll() {
        List<Employer> employers = Dao.getAll();
        return employers;
    }
    public Employer get(String matriculate) {
        if (Dao.get(matriculate) != null){
            return Dao.get(matriculate);
        }else  {
            return  null;
        }
    }
}
