package Database;

import Classes.Skill;

/**
 * Created by guillaume on 6/6/17.
 */
public interface DAOSkills {

    void create(Skill skill);
    boolean update (int companyId, Skill skill);
    Skill read(int skillId);
    boolean delete(int skillId);
    void addSkillToDeveloper(int devId, int skillId);


}
