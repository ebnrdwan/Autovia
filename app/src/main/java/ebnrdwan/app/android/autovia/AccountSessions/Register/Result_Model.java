package ebnrdwan.app.android.autovia.AccountSessions.Register;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Abdulrhman on 17/09/2017.
 */

public class Result_Model {
    @SerializedName("error")
    private Boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("user")
    private User_Model user;

    public Result_Model(Boolean error, String message, User_Model user) {
        this.error = error;
        this.message = message;
        this.user = user;
    }

    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public User_Model getUser() {
        return user;
    }
}
