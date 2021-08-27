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
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    
}
