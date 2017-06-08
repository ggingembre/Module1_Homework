package Database;

import Classes.Skill;

/**
 * Created by guillaume on 6/6/17.
 */
public interface DAOSkills {

    void create(Skill skill);
    void update (int companyId, Skill skill);
    Skill read(int skillId);
    void delete(int skillId);

}
