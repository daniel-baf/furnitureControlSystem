package Domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Bill implements Serializable {

    private int code;
    private int furnitureAssemblyId;
    private String furnitureName;
    private String username;
    private String clientNit;
    private double ammount;
    private LocalDate buyDate;

    public Bill() {
    }

    public Bill(int code) {
        this.code = code;
    }

    public Bill(int code, int furnitureAssemblyId, String username, String clientNit, double ammount, LocalDate buyDate) {
        this.code = code;
        this.furnitureAssemblyId = furnitureAssemblyId;
        this.username = username;
        this.clientNit = clientNit;
        this.ammount = ammount;
        this.buyDate = buyDate;
    }

    public Bill(int code, int furnitureAssemblyId, String furnitureName, String username, String clientNit, double ammount, LocalDate buyDate) {
        this.code = code;
        this.furnitureAssemblyId = furnitureAssemblyId;
        this.furnitureName = furnitureName;
        this.username = username;
        this.clientNit = clientNit;
        this.ammount = ammount;
        this.buyDate = buyDate;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the furnitureAssemblyId
     */
    public int getFurnitureAssemblyId() {
        return furnitureAssemblyId;
    }

    /**
     * @param furnitureAssemblyId the furnitureAssemblyId to set
     */
    public void setFurnitureAssemblyId(int furnitureAssemblyId) {
        this.furnitureAssemblyId = furnitureAssemblyId;
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
     * @return the clientNit
     */
    public String getClientNit() {
        return clientNit;
    }

    /**
     * @param clientNit the clientNit to set
     */
    public void setClientNit(String clientNit) {
        this.clientNit = clientNit;
    }

    /**
     * @return the ammount
     */
    public double getAmmount() {
        return ammount;
    }

    /**
     * @param ammount the ammount to set
     */
    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    /**
     * @return the buyDate
     */
    public LocalDate getBuyDate() {
        return buyDate;
    }

    /**
     * @param buyDate the buyDate to set
     */
    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

}
