package Classes;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by guillaume on 6/6/17.
 */


@Entity
@Table(name = "customers")
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_description")
    private String customerDescription;

    // one customer may have many projects, one project may have many customers, many to many

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Project> projects = new HashSet<Project>(0);
    @JoinTable(name = "customers_projects", joinColumns = {
            @JoinColumn(name = "customer_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "project_id",
                    nullable = false, updatable = false) })


    public Set<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Customer (){}

    public Customer(String customerName, String customerAddress, String customerPhone, String customerDescription) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerDescription = customerDescription;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerDescription() {
        return customerDescription;
    }

    public void setCustomerDescription(String customerDescription) {
        this.customerDescription = customerDescription;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerDescription='" + customerDescription + '\'' +
                '}';
    }
}
