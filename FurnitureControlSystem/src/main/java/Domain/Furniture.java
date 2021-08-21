package Domain;

public class Furniture {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }
}
