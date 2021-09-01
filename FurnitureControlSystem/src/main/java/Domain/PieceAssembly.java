package Domain;

import java.io.Serializable;

public class PieceAssembly implements Serializable {

    private String furnitureName;
    private String pieceName;
    private int cuantity;

    /**
     * Constructor, used to pieces
     *
     * @param furnitureName
     * @param pieceName
     */
    public PieceAssembly(String furnitureName, String pieceName) {
        this.furnitureName = furnitureName;
        this.pieceName = pieceName;
    }

    /**
     * Constructor, used for reports
     *
     * @param furnitureName
     * @param pieceName
     * @param cuantity
     */
    public PieceAssembly(String furnitureName, String pieceName, int cuantity) {
        this.furnitureName = furnitureName;
        this.pieceName = pieceName;
        this.cuantity = cuantity;
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
     * @return the pieceName
     */
    public String getPieceName() {
        return pieceName;
    }

    /**
     * @param pieceName the pieceName to set
     */
    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    /**
     * @return the cuantity
     */
    public int getCuantity() {
        return cuantity;
    }

    /**
     * @param cuantity the cuantity to set
     */
    public void setCuantity(int cuantity) {
        this.cuantity = cuantity;
    }

}
