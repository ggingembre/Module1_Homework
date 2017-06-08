package Database;

import Classes.Project;

import java.sql.*;

/**
 * Created by guillaume on 6/8/17.
 */
public class DAOProjectsImpl implements DAOProjects {

    public void create(Project project) {

        Connection con = null;
        try {
            con = getConnection();
            Statement statement = con.createStatement();
            String sql = "INSERT INTO projects (project_id, project_name, project_description) " +
                    "VALUES (" + project.getProjectId()+ ", '" + project.getProjectName() + "', '" + project.getProjectDescription() + "')";
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

    public void update(int projectId, Project project) {

        try (Connection con = getConnection()){

            PreparedStatement ps = con.prepareStatement("UPDATE projects set " +
                    "project_name=?, " +
                    "project_description=? "+
                    " WHERE project_id=?");

            String name = project.getProjectName();
            String description = project.getProjectDescription();
            int id = projectId;

            ps.setString(1, name);
            ps.setString(2,description);
            ps.setInt(3, id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Project read(int projectId) {

        try(Connection con = getConnection()){

            Statement statement = con.createStatement();
            String sql = "SELECT * FROM projects WHERE project_id =" + projectId;
            ResultSet rs = statement.executeQuery(sql);
            Project project = new Project();

            while (rs.next()){
                String projectName = rs.getString("project_name");
                String projectDescription = rs.getString("project_description");

                project.setProjectName(projectName);
                project.setProjectDescription(projectDescription);

            }

            return project;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int projectId) {

        try (Connection con = getConnection()){
            Statement statement = con.createStatement();
            String sql = "DELETE FROM projects WHERE project_id=" + projectId;
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
