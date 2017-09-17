package ebnrdwan.app.android.autovia.AccountSessions.Register;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Abdulrhman on 16/09/2017.
 */

public class User_Model {


    @SerializedName("name")
    private String Name;
    @SerializedName("email")
    private String Email;
    @SerializedName("password")
    private String Password;
    @SerializedName("phone")
    private String Phone;


    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getPhone() {
        return Phone;
    }
}
