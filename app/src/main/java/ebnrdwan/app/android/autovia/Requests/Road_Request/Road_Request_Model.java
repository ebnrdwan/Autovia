package ebnrdwan.app.android.autovia.Requests.Road_Request;

/**
 * Created by Abdulrhman on 09/09/2017.
 */

public class Road_Request_Model {

    private String RequestNumber;
    private String RequestType;
    private String RequestDate;
    private int RequestState;

    public Road_Request_Model(String requestNumber, String requestType, String requestDate, int requestState) {
        RequestNumber = requestNumber;
        RequestType = requestType;
        RequestDate = requestDate;
        RequestState = requestState;
    }

    public int getRequestState() {
        return RequestState;
    }

    public String getRequestNumber() {
        return RequestNumber;
    }

    public String getRequestType() {
        return RequestType;
    }

    public String getRequestDate() {
        return RequestDate;
    }
}
