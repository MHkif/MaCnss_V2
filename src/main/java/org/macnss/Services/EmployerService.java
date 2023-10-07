package org.macnss.Services;

import org.macnss.dao.impl.EmployerDAO;
import org.macnss.entity.Agent;
import org.macnss.entity.Employer;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class EmployerService implements Service<Employer>{

    private final EmployerDAO DAO = new EmployerDAO();

    public Employer login(String email, String password)  {
        Employer employer = DAO.login(email, password);
        return Objects.nonNull(employer) ? employer : null;
    }

    @Override
    public Employer save(Employer employer){
        return Objects.nonNull(DAO.save(employer))
                ? employer : null;
    }
    @Override
    public Employer update(Employer employer) {
        return Objects.nonNull(DAO.update(employer))
                ? employer: null;
    }

    @Override
    public Employer findBy(String matriculate) {
        return Objects.nonNull(DAO.findBy(matriculate))
                ? DAO.findBy(matriculate): null;
    }

    public List<Employer> getAll() {
        return Objects.nonNull(DAO.getAll())
                ? DAO.getAll() : null;
    }

    public Employer get(String matriculate) {
        return Objects.nonNull(findBy(matriculate) )
                ? findBy(matriculate)  : null;
    }

    public boolean ageVerified(Employer employer){
        Date covertDate = new Date(employer.getBirthDay().getTime());
        Date dateNow = new Date(System.currentTimeMillis());
        long birthdayMs = covertDate.getTime();
        long date_now = dateNow.getTime();
        long timeDiff = Math.abs(date_now - birthdayMs);
        long age = TimeUnit.DAYS.convert(timeDiff,TimeUnit.MILLISECONDS) / 360;

        return  age >= 55;
    }

    public double retirementSalary(Employer employer){
       return DAO.retirementSalary(employer);
    }

    @Override
    public boolean deactivate(String slag) {
        return false;
    }

}
