package Database;

import Classes.Developer;
import java.sql.*;
import Classes.Utils;

import static Classes.Utils.getConnection;

/**
 * Created by guillaume on 6/8/17.
 */
public class DAODevelopersImpl implements DAODevelopers {

    public void create(Developer developer) {
        int dbDevId = -1;
        Connection con = null;
        try {
            con = getConnection();
            Statement statement = con.createStatement();
            String sql = "INSERT INTO developers (id, firstName, lastName, gender, address, email, phone, jobTitle, salary) " +
                    "VALUES (" + developer.getDeveloperId() + ", '" +
                                developer.getFirstName() + "', '" +
                                developer.getLastName() + "', '" +
                                developer.getGender() + "' , '" +
                                developer.getAddress() + "' , '" +
                                developer.getEmail() + "', '" +
                                developer.getPhone() + "', '" +
                                developer.getJobTitle() + "', " +
                                developer.getSalary() + ")";

            statement.execute(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                dbDevId = rs.getInt(1);
            } else {

                System.out.println("error in retrieving auto generated ID key from DB");
            }

            developer.setDeveloperId(dbDevId);

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


    public void update(int developerId, Developer developer) {

        try (Connection con = getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE developers set " +
                    "firstName =?, " +
                    "lastName=?, "+
                    "gender=?,"+
                    "address=?," +
                    "email=?," +
                    "phone=?," +
                    "jobTitle=?," +
                    "salary=?" +
                    " WHERE id=?");

            String firstName = developer.getFirstName();
            String lastName = developer.getLastName();
            String gender = developer.getGender();
            String address = developer.getAddress();
            String email = developer.getEmail();
            String phone = developer.getPhone();
            String title = developer.getJobTitle();
            double salary = developer.getSalary();
            int id = developerId;

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, gender);
            ps.setString(4,address);
            ps.setString(5,email);
            ps.setString(6, phone);
            ps.setString(7, title);
            ps.setDouble(8,salary);
            ps.setInt(9, id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public Developer read(int developerId) {
        try(Connection con = getConnection()){

            Statement statement = con.createStatement();
            String sql = "SELECT * FROM developers WHERE id =" + developerId;
            ResultSet rs = statement.executeQuery(sql);
            Developer developer = new Developer();

            while (rs.next()){
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String title = rs.getString("jobTitle");
                Double salary = rs.getDouble("salary");

                developer.setFirstName(firstName);
                developer.setLastName(lastName);
                developer.setGender(gender);
                developer.setAddress(address);
                developer.setEmail(email);
                developer.setPhone(phone);
                developer.setJobTitle(title);
                developer.setSalary(salary);
            }

            return developer;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(int developerId) {

        try (Connection con = getConnection()){
            Statement statement = con.createStatement();
            String sql = "DELETE FROM developers WHERE id=" + developerId;
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
