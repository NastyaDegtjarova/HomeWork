package model;

/**
 * Created by Nastya on 20.11.2017.
 */
public class Customer {
    private Long idCustomer;
    private String firstNameCust;
    private String lastNameCust;

    public Customer() {
    }

    public Customer(Long idCustomer, String firstNameCust, String lastNameCust) {
        this.idCustomer = idCustomer;
        this.firstNameCust = firstNameCust;
        this.lastNameCust = lastNameCust;

    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getFirstNameCust() {
        return firstNameCust;
    }

    public void setFirstNameCust(String firstNameCust) {
        this.firstNameCust = firstNameCust;
    }

    public String getLastNameCust() {
        return lastNameCust;
    }

    public void setLastNameCust(String lastNameCust) {
        this.lastNameCust = lastNameCust;
    }

    public Customer withIdCust(Long idCustomer){
        this.idCustomer = idCustomer;
        return this;
    }

    public Customer withFirstNameCust(String firstNameCust){
        this.firstNameCust = firstNameCust;
        return this;
    }

    public Customer withLastNameCust(String lastNameCust){
        this.lastNameCust = lastNameCust;
        return this;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer =" + idCustomer +
                ", firstNameCust ='" + firstNameCust + '\'' +
                ", lastNameCust ='" + lastNameCust + '\'' +
                '}';
    }
}