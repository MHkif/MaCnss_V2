package org.macnss.Services;

import org.macnss.dao.impl.EmployerDAO;
import org.macnss.entity.Company;
import org.macnss.entity.Employer;
import org.macnss.entity.Folder;


import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
        return  Objects.nonNull(DAO.update(employer))
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

    public List<Folder> getAllMyFolders(List<Folder> folders, Employer employer){
       return folders.stream().filter(folder -> folder.getEmployer().getMatriculate().equals(employer.getMatriculate())).collect(Collectors.toList());
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

    public int workedDays(Employer employer){
       return DAO.workedDays(employer);
    }
    public double retirementSalary(Employer employer){
       return DAO.retirementSalary(employer);
    }

    @Override
    public boolean deactivate(String slag) {
        return false;
    }

    public boolean associateEmployerWithCompany( Employer employer, Company company, int days ){
        return DAO.associateEmployerWithCompany(employer, company, days);
    }

}
