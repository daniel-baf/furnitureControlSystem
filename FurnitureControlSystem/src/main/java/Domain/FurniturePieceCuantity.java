package Domain;

public class FurniturePieceCuantity {

    private FurniturePiece furnPiece;
    private int cuantity;

    public FurniturePieceCuantity() {
    }

    public FurniturePieceCuantity(FurniturePiece furnPiece, int cuantity) {
        this.furnPiece = furnPiece;
        this.cuantity = cuantity;
    }

    public FurniturePiece getFurnPiece() {
        return furnPiece;
    }

    public void setFurnPiece(FurniturePiece furnPiece) {
        this.furnPiece = furnPiece;
    }

    public int getCuantity() {
        return cuantity;
    }

    public void setCuantity(int cuantity) {
        this.cuantity = cuantity;
    }

}
