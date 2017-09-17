package ebnrdwan.app.android.autovia.AccountSessions.Register;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abdulrhman on 16/09/2017.
 */

public class ApiClient {

    public static final String BASE_URL="http://shopaiksdn.com";

    public static Retrofit retrofit= null;

    public static Retrofit getApiClientInstance(){

        if (retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
