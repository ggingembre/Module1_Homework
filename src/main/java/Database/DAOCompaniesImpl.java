package Database;

import Classes.Company;

import java.sql.*;

/**
 * Created by guillaume on 6/7/17.
 */
public class DAOCompaniesImpl implements DAOCompanies {

    public void create(Company company) {

        Connection con = null;
        try {
            con = getConnection();
            Statement statement = con.createStatement();
            String sql = "INSERT INTO companies (company_id, company_name, company_address, company_description) " +
                    "VALUES (" + company.getId() + ", '" + company.getCompanyName() + "', '" + company.getCompanyAddress() +
                    "' , '" + company.getCompanyDescription() + "')";
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

    public void update(int companyId, Company company) {

        try (Connection con = getConnection()){
            //Statement statement = con.createStatement();
            PreparedStatement ps = con.prepareStatement("UPDATE companies set company_name =?, " +
                    "company_address=?, company_description=? WHERE company_id=?");

            String name = company.getCompanyName();
            String address = company.getCompanyAddress();
            String description = company.getCompanyDescription();
            int id = companyId;

            ps.setString(1, name);
            ps.setString(2,address);
            ps.setString(3,description);
            ps.setInt(4, id);
            ps.execute();

            //String sql = "UPDATE companies set company_name = '" + name + "', company_address = '" +
            //        address + "', company_description = '" + description + "' WHERE company_id =" + companyId;

            //statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Company read(int companyId) {

        try(Connection con = getConnection()){

            Statement statement = con.createStatement();
            String sql = "SELECT * FROM companies WHERE company_id =" + companyId;
            ResultSet rs = statement.executeQuery(sql);
            Company company = new Company();

            while (rs.next()){
                String companyName = rs.getString("company_name");
                String companyAddress = rs.getString("company_address");
                String companyDescription = rs.getString("company_description");

                company.setCompanyName(companyName);
                company.setCompanyAddress(companyAddress);
                company.setCompanyDescription(companyDescription);

            }

            return company;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void delete(int companyId) {

        try (Connection con = getConnection()){
            Statement statement = con.createStatement();
            String sql = "DELETE FROM companies WHERE company_id=" + companyId;
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