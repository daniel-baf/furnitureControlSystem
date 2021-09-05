package TransactionObjects;

import java.io.Serializable;

public class BillFurniture implements Serializable {

    private String furniture;
    private int id;
    private double sellPrice;
    private int sold;

    public BillFurniture() {
    }

    public BillFurniture(String furniture, int id, double sellPrice, int sold) {
        this.furniture = furniture;
        this.id = id;
        this.sellPrice = sellPrice;
        this.sold = sold;
    }

    /**
     * @return the furniture
     */
    public String getFurniture() {
        return furniture;
    }

    /**
     * @param furniture the furniture to set
     */
    public void setFurniture(String furniture) {
        this.furniture = furniture;
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
}
