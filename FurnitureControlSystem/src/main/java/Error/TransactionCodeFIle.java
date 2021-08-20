package Error;

public class TransactionCodeFIle {
    private int lineError;
    private String sentece;
    private String values;
    private String status; // 0 = no error

    public TransactionCodeFIle() {
    }

    public TransactionCodeFIle(int lineError) {
        this.lineError = lineError;
    }

    public TransactionCodeFIle(int line, String sentece, String values, String error) {
        this.lineError = line;
        this.sentece = sentece;
        this.values = values;
        this.status = error;
    }

    public int getLine() {
        return lineError;
    }

    public void setLine(int line) {
        this.lineError = line;
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

    public void setStatus(String error) {
        this.status = error;
    }
    
    
}
