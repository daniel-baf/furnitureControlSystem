package TransactionObjects;

public class InsertObjectStatus {

    private int lineError;
    private String sentece;
    private String values;
    private String lineRead;
    private String status; // 0 = no error
    private String[] valuesSplited;
    
    public InsertObjectStatus() {
    }

    public InsertObjectStatus(int lineError) {
        this.lineError = lineError;
    }

    public InsertObjectStatus(int line, String sentece, String values, String error) {
        this.lineError = line;
        this.sentece = sentece;
        this.values = values;
        this.status = error;
    }

    public int getLineError() {
        return lineError;
    }

    public void setLineError(int lineError) {
        this.lineError = lineError;
    }

    public String getSentece() {
        return sentece;
    }

    public void setSentece(String sentece) {
        this.sentece = sentece;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getValuesSplited() {
        return valuesSplited;
    }

    public void setValuesSplited(String[] valuesSplited) {
        this.valuesSplited = valuesSplited;
    }

    public String getLineRead() {
        return lineRead;
    }

    public void setLineRead(String lineRead) {
        this.lineRead = lineRead;
    }
    
    
}
