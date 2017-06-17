package Database;

import Classes.Customer;

/**
 * Created by guillaume on 6/6/17.
 */
public interface DAOCustomers {

    void create(Customer customer);
    boolean update (int customerId, Customer customer);
    Customer read(int customerId);
    boolean delete(int customerId);
    void addProjectToCustomer(int projectId, int custId);

}

