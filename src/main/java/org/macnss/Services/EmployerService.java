package org.macnss.Services;

import org.macnss.dao.impl.EmployerDAO;
import org.macnss.entity.Employer;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class EmployerService implements Service<Employer>{

    private final EmployerDAO DAO = new EmployerDAO();

    @Override
    public Employer save(Employer employer){
        return Objects.nonNull(DAO.save(employer))
                ? DAO.save(employer) : null;
    }
    @Override
    public Employer update(Employer employer) {
        return Objects.nonNull(DAO.update(employer))
                ? DAO.update(employer): null;
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
        long daysDiff = TimeUnit.DAYS.convert(timeDiff,TimeUnit.MILLISECONDS);
        return  daysDiff >= 55;
    }

    public double retirementSalary(Employer employer){
        return 0;
    }

    @Override
    public boolean deactivate(String slag) {
        return false;
    }

}
