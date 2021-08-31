package Domain;

import java.io.Serializable;

public class Furniture implements Serializable {

    private String name;
    private double sellPrice;

    public Furniture() {
    }

    public Furniture(String name) {
        this.name = name;
    }

    public Furniture(String name, double sellPrice) {
        this.name = name;
        this.sellPrice = sellPrice;
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
     * @return the sellPrice
     */
    public double getSellPrice() {
        return sellPrice;
    }

    /**
     * @param sellPrice the sellPrice to set
     */
    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

}
