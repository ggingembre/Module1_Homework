package Classes;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by guillaume on 6/6/17.
 */

@Entity
@Table(name = "companies")
@NamedQuery(name = "Company.findAll", query = "SELECT c FROM Customer c")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_address")
    private String companyAddress;

    @Column(name = "company_description")
    private String companyDescription;

    // a company has many developers, and a developer has many companies (if freelance), many to many

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Developer> developers = new HashSet<Developer>(0);

    @JoinTable(name = "companies_developers", joinColumns = {
            @JoinColumn(name = "company_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "developer_id",
                    nullable = false, updatable = false) })

    public Set<Developer> getDevelopers() {
        return this.developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    public Company (){}

    public Company(String companyName, String companyAddress, String companyDescription) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyDescription = companyDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }





    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyDescription='" + companyDescription + '\'' +
                '}';
    }
}
