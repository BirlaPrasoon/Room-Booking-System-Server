import com.google.gson.*;
import entities.*;
import responseObjects.RequestResponse;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

public class Main {

    private static Gson gson = new GsonBuilder().create();

    public static void main(String[] args) throws IOException, SQLException {

//        Booking.makeRequest(121212129,"finally submitting request",0,0,0,0,"9000AM","1000AM","LT4","2018-12-14","granter1@lnmiit.ac.in","16ucs136@lnmiit.ac.in");


//          System.out.println(Requester.setRequestStatus("LT2",4,"approved"));

//        List<Timeline> rooms = Room.getRoomTimelineOnDate("2018-12-13","LT2");
//        JsonArray array = new JsonArray();
//
//        for(Timeline r: rooms){
//            JsonElement json = gson.toJsonTree(r);
//            array.add(json);
//        }
//
//        System.out.println(array);

//        URL url = new URL("http://localhost:3306/login");
//        URLConnection conn = url.openConnection();
//        conn.setRequestProperty("heiii","byyy");
//
//        List<RequestResponse> requests = entities.Requester.getCancellableRequests("16ucs136@lnmiit.ac.in");
//        JsonArray array = new JsonArray();
//
//        for (RequestResponse r : requests) {
//            JsonElement json = gson.toJsonTree(r);
//            array.add(json);
//        }
//

        List<RequestResponse> requests = Requester.getCancellableRequests("16ucs136@lnmiit.ac.in");
        JsonArray array = new JsonArray();

        for (RequestResponse r : requests) {
            JsonElement json = gson.toJsonTree(r);
            array.add(json);
        }
        System.out.println(array);
    }
}
