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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
