package Classes;

/**
 * Created by guillaume on 6/6/17.
 */
public class Company {

    private int id;
    private String companyName;
    private String companyAddress;
    private String companyDescription;

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
}
