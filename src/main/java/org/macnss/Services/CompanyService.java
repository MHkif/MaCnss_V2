package org.macnss.Services;

import org.macnss.dao.impl.CompanyDAO;
import org.macnss.entity.Company;

import java.util.List;
import java.util.Objects;

public class CompanyService implements Service<Company> {

    private final CompanyDAO DAO = new CompanyDAO();
    @Override
    public Company save(Company company) {
        return Objects.nonNull(DAO.save(company))
                ? DAO.save(company) : null;
    }

    @Override
    public Company update(Company company) {
        return Objects.nonNull(DAO.update(company))
                ? DAO.update(company) : null;
    }

    @Override
    public Company findBy(String id) {
        return Objects.nonNull(DAO.findBy(id))
                ? DAO.findBy(id) : null;
    }

    @Override
    public List<Company> getAll() {
        return Objects.nonNull(DAO.getAll())
                ? DAO.getAll() : null;
    }

    @Override
    public boolean deactivate(String slag) {
        return false;
    }


}