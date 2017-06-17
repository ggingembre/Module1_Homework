package Database;

import Classes.Customer;
import Classes.Utils;

import java.sql.*;

import static Classes.Utils.getConnection;

/**
 * Created by guillaume on 6/8/17.
 */
public class DAOCustomersImpl implements DAOCustomers {



    public void create(Customer customer) {
        int dbCustId = -1;
        Connection con = null;
        try {
            con = getConnection();
            Statement statement = con.createStatement();
            String sql = "INSERT INTO customers (customer_id, customer_name, customer_address, customer_phone, customer_description) " +
                    "VALUES (" + customer.getCustomerId() + ", '" + customer.getCustomerName() + "', '" + customer.getCustomerAddress() +
                    "' , '" + customer.getCustomerPhone() + "', '" + customer.getCustomerDescription() + "')";
            statement.execute(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                dbCustId = rs.getInt(1);
            } else {

                System.out.println("error in retrieving auto generated ID key from DB");
            }

            customer.setCustomerId(dbCustId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con!=null){
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public boolean update(int customerId, Customer customer) {

        boolean success = false;

        try (Connection con = getConnection()){
        PreparedStatement ps = con.prepareStatement("UPDATE customers set customer_name =?, " +
                "customer_address=?, customer_phone=?, customer_description=? WHERE customer_id=?");

        String name = customer.getCustomerName();
        String address = customer.getCustomerAddress();
        String phone = customer.getCustomerPhone();
        String description = customer.getCustomerDescription();
        int id = customerId;

        ps.setString(1, name);
        ps.setString(2,address);
        ps.setString(3,description);
        ps.setString(4, phone);
        ps.setInt(5, id);
        ps.execute();

        success = true;

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    return success;

    }


    public Customer read(int customerId) {
        try(Connection con = getConnection()){

            Statement statement = con.createStatement();
            String sql = "SELECT * FROM customers WHERE customer_id =" + customerId;
            ResultSet rs = statement.executeQuery(sql);
            Customer customer = new Customer();

            while (rs.next()){
                String customerName = rs.getString("customer_name");
                String customerAddress = rs.getString("customer_address");
                String customerPhone = rs.getString("customer_phone");
                String customerDescription = rs.getString("customer_description");

                customer.setCustomerName(customerName);
                customer.setCustomerAddress(customerAddress);
                customer.setCustomerPhone(customerPhone);
                customer.setCustomerDescription(customerDescription);

            }

            return customer;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean delete(int customerId) {

        boolean success = false;

        try (Connection con = getConnection()){
            Statement statement = con.createStatement();
            String sql = "DELETE FROM customers WHERE customer_id=" + customerId;
            statement.execute(sql);
            success = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return success;

    }

    public void addProjectToCustomer(int projectId, int custId){

        Connection con = null;
        try {
            con = getConnection();
            Statement statement = con.createStatement();
            String sql = "INSERT INTO customers_projects (customer_id, project_id) " +
                    "VALUES (" + custId + ", " + projectId  + ")";
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con!=null){
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }


}
