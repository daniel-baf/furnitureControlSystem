package TransactionObjects;

public class PieceByStock {

    private int stock;
    private String name;

    public PieceByStock() {
    }

    public PieceByStock(int stock, String name) {
        this.stock = stock;
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
