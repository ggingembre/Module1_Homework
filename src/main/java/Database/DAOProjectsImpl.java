package Database;

import Classes.Project;

import java.sql.*;

import Classes.Utils;

import static Classes.Utils.getConnection;

/**
 * Created by guillaume on 6/8/17.
 */
public class DAOProjectsImpl implements DAOProjects {

    public void create(Project project) {
        int dbProjectId = -1;
        Connection con = null;
        try {
            con = getConnection();
            Statement statement = con.createStatement();
            String sql = "INSERT INTO projects (project_id, project_name, project_description) " +
                    "VALUES (" + project.getProjectId()+ ", '" + project.getProjectName() + "', '" + project.getProjectDescription() + "')";
            statement.execute(sql, Statement.RETURN_GENERATED_KEYS); // rs = stmt.getGeneratedKeys();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                dbProjectId = rs.getInt(1);
            } else {

                System.out.println("error in retrieving auto generated ID key from DB");
            }

            project.setProjectId(dbProjectId);

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

    public boolean update(int projectId, Project project) {

        boolean success = false;

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

            success = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return success;

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

    public boolean delete(int projectId) {

        boolean success = false;

        try (Connection con = getConnection()){
            Statement statement = con.createStatement();
            String sql = "DELETE FROM projects WHERE project_id=" + projectId;
            statement.execute(sql);
            success = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return success;
    }

    public void addProjectToDeveloper(int projectId, int devId){

        Connection con = null;
        try {
            con = getConnection();
            Statement statement = con.createStatement();
            String sql = "INSERT INTO project_developers (project_id, developer_id) " +
                    "VALUES (" + projectId + ", " + devId  + ")";
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
