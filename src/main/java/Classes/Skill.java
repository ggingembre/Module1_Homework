package Classes;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by guillaume on 6/6/17.
 */

@Entity
@Table(name = "skills")
@NamedQuery(name = "Skill.findAll", query = "SELECT s FROM Skill s")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int skillId;

    @Column(name = "skillName")
    private String skillName;

    @Column(name = "skillDescription")
    private String skillDescription;

    // one developer has many skills, and one skill has many developers -> many to many

    @ManyToMany(mappedBy = "skills")
    private Collection<Developer> developers;

    public Collection<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Collection<Developer> developers) {
        this.developers = developers;
    }


    public Skill (){}

    public Skill(String skillName, String skillDescription) {
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

    @Override
    public String toString() {
        return "Skill{" +
                "skillId=" + skillId +
                ", skillName='" + skillName + '\'' +
                ", skillDescription='" + skillDescription + '\'' +
                '}';
    }

}
