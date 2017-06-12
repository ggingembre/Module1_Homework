package Database;

import Classes.Skill;

import java.sql.*;

import static Classes.Utils.getConnection;

/**
 * Created by guillaume on 6/8/17.
 */
public class DAOSkillsImpl implements DAOSkills {


    public void create(Skill skill) {
        int dbSkillId = -1;
        Connection con = null;
        try {
            con = getConnection();
            Statement statement = con.createStatement();
            String sql = "INSERT INTO skills (skillName, skillDescription) " +
                    "VALUES ('" + skill.getSkillName() + "', '" + skill.getSkillDescription() + "')";
            statement.execute(sql, Statement.RETURN_GENERATED_KEYS); // rs = stmt.getGeneratedKeys();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                 dbSkillId = rs.getInt(1);
            } else {

                System.out.println("error in retrieving auto generated ID key from DB");
            }

            skill.setSkillId(dbSkillId);

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

    public void update(int skillId, Skill skill) {

        try (Connection con = getConnection()){

            PreparedStatement ps = con.prepareStatement("UPDATE skills set " +
                    "skillName =?, " +
                    "skillDescription=? "+
                    " WHERE id=?");

            String name = skill.getSkillName();
            String description = skill.getSkillDescription();
            int id = skillId;

            ps.setString(1, name);
            ps.setString(2,description);
            ps.setInt(3, id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Skill read(int skillId) {

        try(Connection con = getConnection()){

            Statement statement = con.createStatement();
            String sql = "SELECT * FROM skills WHERE id =" + skillId;
            ResultSet rs = statement.executeQuery(sql);
            Skill skill = new Skill();

            while (rs.next()){
                String skillName = rs.getString("skillName");
                String skillDescription = rs.getString("skillDescription");

                skill.setSkillName(skillName);
                skill.setSkillDescription(skillDescription);

            }

            return skill;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int skillId) {

        try (Connection con = getConnection()){
            Statement statement = con.createStatement();
            String sql = "DELETE FROM skills WHERE id=" + skillId;
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSkillToDeveloper(int devId, int skillId){

        Connection con = null;
        try {
            con = getConnection();
            Statement statement = con.createStatement();
            String sql = "INSERT INTO developers_skills (developer_id, skill_id) " +
                    "VALUES (" + devId + ", " + skillId  + ")";
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
