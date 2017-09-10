package ebnrdwan.app.android.autovia.Requests.CarCareRequest;

/**
 * Created by Abdulrhman on 09/09/2017.
 */

public class Care_Request_Model {

    private String RequestNumber;
    private String RequestType;
    private String RequestDate;
    private String messageInfo;

    public String getMessageInfo() {
        return messageInfo;
    }

    private int RequestState;

    public Care_Request_Model(String requestNumber, String requestType, String requestDate, String messageInfo, int requestState) {
        RequestNumber = requestNumber;
        RequestType = requestType;
        RequestDate = requestDate;
        this.messageInfo = messageInfo;
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
