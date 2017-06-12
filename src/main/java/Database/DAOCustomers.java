package Database;

import Classes.Customer;

/**
 * Created by guillaume on 6/6/17.
 */
public interface DAOCustomers {

    void create(Customer customer);
    void update (int customerId, Customer customer);
    Customer read(int customerId);
    void delete(int customerId);
    void addProjectToCustomer(int projectId, int custId);

}

