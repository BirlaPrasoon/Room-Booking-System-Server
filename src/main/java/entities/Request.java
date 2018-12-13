package entities;

public class Request {

    private int request_id;
    private String purpose;
    private int mic;
    private int ac;
    private int cleaning;
    private int projector;
    private String from_time;
    private String to_time;
    private String status;

    public Request() {
    }

    public Request(int request_id, String purpose, int mic, int ac, int cleaning, int projector, String from_time, String to_time, String status) {
        this.request_id = request_id;
        this.purpose = purpose;
        this.mic = mic;
        this.ac = ac;
        this.cleaning = cleaning;
        this.projector = projector;
        this.from_time = from_time;
        this.to_time = to_time;
        this.status = status;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getMic() {
        return mic;
    }

    public void setMic(int mic) {
        this.mic = mic;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public int getCleaning() {
        return cleaning;
    }

    public void setCleaning(int cleaning) {
        this.cleaning = cleaning;
    }

    public int getProjector() {
        return projector;
    }

    public void setProjector(int projector) {
        this.projector = projector;
    }

    public String getFrom_time() {
        return from_time;
    }

    public void setFrom_time(String from_time) {
        this.from_time = from_time;
    }

    public String getTo_time() {
        return to_time;
    }

    public void setTo_time(String to_time) {
        this.to_time = to_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
