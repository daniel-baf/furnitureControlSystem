package Domain;

public class FurniturePiece {

    private int id; // autoincrement
    private String name;
    private double cost;

    public FurniturePiece() {
    }

    public FurniturePiece(int id) {
        this.id = id;
    }

    public FurniturePiece(String name) {
        this.name = name;
    }

    public FurniturePiece(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public FurniturePiece(int id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}
