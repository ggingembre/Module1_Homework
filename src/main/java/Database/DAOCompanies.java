package Database;

import Classes.Company;

/**
 * Created by guillaume on 6/6/17.
 */
public interface DAOCompanies {

    void create(Company company);
    void update (int companyId, Company company);
    Company read(int companyId);
    void delete(int companyId);
    void addCompanyToDeveloper(int compId, int devId);

}
