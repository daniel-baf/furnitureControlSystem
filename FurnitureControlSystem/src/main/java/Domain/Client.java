package Domain;

public class Client {

    private String nit;
    private String name;
    private String adress;
    private String municipality;
    private String department;

    public Client() {
    }

    public Client(String nit) {
        this.nit = nit;
    }

    public Client(String nit, String name, String adress) {
        this.nit = nit;
        this.name = name;
        this.adress = adress;
    }

    public Client(String nit, String name, String adress, String municipality, String department) {
        this.nit = nit;
        this.name = name;
        this.adress = adress;
        this.municipality = municipality;
        this.department = department;
    }

    /**
     * @return the nit
     */
    public String getNit() {
        return nit;
    }

    /**
     * @param nit the nit to set
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     * @param adress the adress to set
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * @return the municipality
     */
    public String getMunicipality() {
        return municipality;
    }

    /**
     * @param municipality the municipality to set
     */
    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

}
