package Classes;

/**
 * Created by guillaume on 6/6/17.
 */
public class Skill {

    private int skillId;
    private String skillName;
    private String skillDescription;

    public Skill (){}

    public Skill(int skillId, String skillName, String skillDescription) {
        this.skillId = skillId;
        this.skillName = skillName;
        this.skillDescription = skillDescription;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }
}
