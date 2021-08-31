package Domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Refund implements Serializable {

    private int billID;
    private LocalDate refundDate;
    private LocalDate buyDate;
    private double refund;
    private String clientNit;
    private double priceAssembly;
    private double moneyLost;
    private String furnitureName;

    public Refund() {
    }

    public Refund(int billID, LocalDate refundDate) {
        this.billID = billID;
        this.refundDate = refundDate;
    }

    public Refund(int billID, LocalDate refundDate, LocalDate buyDate) {
        this.billID = billID;
        this.refundDate = refundDate;
        this.buyDate = buyDate;
    }

    public Refund(int billID, String furnitureName, LocalDate refundDate, LocalDate buyDate, double refund, String clientNit, double priceAssembly, double moneyLost) {
        this.billID = billID;
        this.furnitureName = furnitureName;
        this.refundDate = refundDate;
        this.buyDate = buyDate;
        this.refund = refund;
        this.clientNit = clientNit;
        this.priceAssembly = priceAssembly;
        this.moneyLost = moneyLost;
    }

    /**
     * @return the billID
     */
    public int getBillID() {
        return billID;
    }

    /**
     * @param billID the billID to set
     */
    public void setBillID(int billID) {
        this.billID = billID;
    }

    /**
     * @return the refundDate
     */
    public LocalDate getRefundDate() {
        return refundDate;
    }

    /**
     * @param refundDate the refundDate to set
     */
    public void setRefundDate(LocalDate refundDate) {
        this.refundDate = refundDate;
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

    /**
     * @return the refund
     */
    public double getRefund() {
        return refund;
    }

    /**
     * @param refund the refund to set
     */
    public void setRefund(double refund) {
        this.refund = refund;
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
     * @return the priceAssembly
     */
    public double getPriceAssembly() {
        return priceAssembly;
    }

    /**
     * @param priceAssembly the priceAssembly to set
     */
    public void setPriceAssembly(double priceAssembly) {
        this.priceAssembly = priceAssembly;
    }

    /**
     * @return the moneyLost
     */
    public double getMoneyLost() {
        return moneyLost;
    }

    /**
     * @param moneyLost the moneyLost to set
     */
    public void setMoneyLost(double moneyLost) {
        this.moneyLost = moneyLost;
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

}
