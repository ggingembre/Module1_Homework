package Service;

import Classes.Company;
import Database.DAOCompaniesImpl;

/**
 * Created by guillaume on 8/19/17.
 */
public class CompanyService {

    private static DAOCompaniesImpl compDao;

    public CompanyService() {
        compDao = new DAOCompaniesImpl();
    }

    public void create (Company entity) {
        compDao.openCurrentSessionwithTransaction();
        compDao.create(entity);
        compDao.closeCurrentSessionwithTransaction();
    }

    public void update(int compId, Company company) {
        compDao.openCurrentSessionwithTransaction();
        compDao.update(compId, company);
        compDao.closeCurrentSessionwithTransaction();
    }

    public Company read(int id) {
        compDao.openCurrentSession();
        Company company = compDao.read(id);
        compDao.closeCurrentSession();
        return company;
    }

    public void delete(int id) {
        compDao.openCurrentSessionwithTransaction();
        compDao.delete(id);
        compDao.closeCurrentSessionwithTransaction();
    }

    public DAOCompaniesImpl compDao() {
        return compDao;
    }

}
