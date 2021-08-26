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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public double getAssemblyPrice() {
        return assemblyPrice;
    }

    public void setAssemblyPrice(double assemblyPrice) {
        this.assemblyPrice = assemblyPrice;
    }

}
