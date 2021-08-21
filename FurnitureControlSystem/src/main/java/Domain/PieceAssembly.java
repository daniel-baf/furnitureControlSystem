package Domain;

public class PieceAssembly {

    private String furnitureName;
    private String pieceName;
    private int cuantity;

    public PieceAssembly(String furnitureName, String pieceName) {
        this.furnitureName = furnitureName;
        this.pieceName = pieceName;
    }

    public PieceAssembly(String furnitureName, String pieceName, int cuantity) {
        this.furnitureName = furnitureName;
        this.pieceName = pieceName;
        this.cuantity = cuantity;
    }

    public PieceAssembly(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public String getPieceName() {
        return pieceName;
    }

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    public int getCuantity() {
        return cuantity;
    }

    public void setCuantity(int cuantity) {
        this.cuantity = cuantity;
    }

    
}
