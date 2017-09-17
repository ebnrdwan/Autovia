package ebnrdwan.app.android.autovia.AccountSessions.Register;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Abdulrhman on 16/09/2017.
 */

public interface ApiInterface {
    @FormUrlEncoded
    @POST("/autovia_service/app/user/register")
    Call<User_Model> RegisterUserCall(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("phone") String phone);
}
