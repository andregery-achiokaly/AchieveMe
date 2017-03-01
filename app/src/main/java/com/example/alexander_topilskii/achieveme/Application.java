package com.example.alexander_topilskii.achieveme;


import com.deploygate.sdk.DeployGate;
import com.example.alexander_topilskii.achieveme.model.rest.RestApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Application extends android.app.Application {

    private static RestApi restApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        DeployGate.install(this);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://achievements17.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restApi = retrofit.create(RestApi.class); 
    }

    public static RestApi getApi() {
        return restApi;
    }
}
