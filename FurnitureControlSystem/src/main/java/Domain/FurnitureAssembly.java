package Domain;

import java.time.LocalDate;

public class FurnitureAssembly {

    private int id;
    private String username;
    private LocalDate date;
    private int sold; // 1 = sold, 0 = no sold, 3 = refunded
    private String furnitureName;
    private double assemblyPrice;

    public FurnitureAssembly() {
    }

    public FurnitureAssembly(int id) {
        this.id = id;
    }

    public FurnitureAssembly(String username, LocalDate date, String furnitureName) {
        this.username = username;
        this.date = date;
        this.furnitureName = furnitureName;
    }

    public FurnitureAssembly(int id, String username, LocalDate date, int sold, String furnitureName, double assemblyPrice) {
        this.id = id;
        this.username = username;
        this.date = date;
        this.sold = sold;
        this.furnitureName = furnitureName;
        this.assemblyPrice = assemblyPrice;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return the sold
     */
    public int getSold() {
        return sold;
    }

    /**
     * @param sold the sold to set
     */
    public void setSold(int sold) {
        this.sold = sold;
    }

    /**
     * @return the furnitureName
     */
    public String getFurnitureName() {
        return furnitureName;
    }

    /**
     * @param furnitureName the furnitureName to set
     */
    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    /**
     * @return the assemblyPrice
     */
    public double getAssemblyPrice() {
        return assemblyPrice;
    }

    /**
     * @param assemblyPrice the assemblyPrice to set
     */
    public void setAssemblyPrice(double assemblyPrice) {
        this.assemblyPrice = assemblyPrice;
    }
    
    
}
