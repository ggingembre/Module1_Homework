package Database;

import Classes.Customer;

import java.sql.*;

/**
 * Created by guillaume on 6/8/17.
 */
public class DAOCustomersImpl implements DAOCustomers {



    public void create(Customer customer) {

        Connection con = null;
        try {
            con = getConnection();
            Statement statement = con.createStatement();
            String sql = "INSERT INTO customers (customer_id, customer_name, customer_address, customer_phone, customer_description) " +
                    "VALUES (" + customer.getCustomerId() + ", '" + customer.getCustomerName() + "', '" + customer.getCustomerAddress() +
                    "' , '" + customer.getCustomerPhone() + "', '" + customer.getCustomerDescription() + "')";
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


    public void update(int customerId, Customer customer) {

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

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

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


    public void delete(int customerId) {

        try (Connection con = getConnection()){
            Statement statement = con.createStatement();
            String sql = "DELETE FROM customers WHERE customer_id=" + customerId;
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/homework11" +
                        "?serverTimezone=UTC" +
                        "&autoReconnect=true&useSSL=false",
                "root","1");

    }
}
