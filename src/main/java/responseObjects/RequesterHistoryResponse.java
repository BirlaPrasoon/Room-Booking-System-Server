package responseObjects;

public class RequesterHistoryResponse {

    private String roomId,date,from_time,to_time,status;

    public RequesterHistoryResponse(String roomId, String date, String from_time, String to_time, String status) {
        this.roomId = roomId;
        this.date = date;
        this.from_time = from_time;
        this.to_time = to_time;
        this.status = status;
    }

    public RequesterHistoryResponse() { }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
