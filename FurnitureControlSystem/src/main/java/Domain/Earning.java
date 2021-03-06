package Domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Earning implements Serializable {

    private int billId;
    private double earning;
    private double sellAmmount;
    private String item;
    private short furnitureState;
    private LocalDate sellDate;
    private String worker;

    public Earning() {
    }

    public Earning(int billId, double earning, double sellAmmount, String item, short state, LocalDate sellDate, String worker) {
        this.billId = billId;
        this.earning = Math.round(earning * 100.00) / 100.00;
        this.sellAmmount = Math.round(sellAmmount * 100.00) / 100.00;
        this.item = item;
        this.furnitureState = state;
        this.sellDate = sellDate;
        this.worker = worker;
    }

    /**
     * @return the billId
     */
    public int getBillId() {
        return billId;
    }

    /**
     * @param billId the billId to set
     */
    public void setBillId(int billId) {
        this.billId = billId;
    }

    /**
     * @return the earning
     */
    public double getEarning() {
        return earning;
    }

    /**
     * @param earning the earning to set
     */
    public void setEarning(double earning) {
        this.earning = earning;
    }

    /**
     * @return the sellAmmount
     */
    public double getSellAmmount() {
        return sellAmmount;
    }

    /**
     * @param sellAmmount the sellAmmount to set
     */
    public void setSellAmmount(double sellAmmount) {
        this.sellAmmount = sellAmmount;
    }

    /**
     * @return the item
     */
    public String getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * @return the furnitureState
     */
    public short getFurnitureState() {
        return furnitureState;
    }

    /**
     * @param furnitureState the furnitureState to set
     */
    public void setFurnitureState(short furnitureState) {
        this.furnitureState = furnitureState;
    }

    /**
     * @return the sellDate
     */
    public LocalDate getSellDate() {
        return sellDate;
    }

    /**
     * @param sellDate the sellDate to set
     */
    public void setSellDate(LocalDate sellDate) {
        this.sellDate = sellDate;
    }

    /**
     * @return the worker
     */
    public String getWorker() {
        return worker;
    }

    /**
     * @param worker the worker to set
     */
    public void setWorker(String worker) {
        this.worker = worker;
    }

}
