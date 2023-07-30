package com.example.news_app;

import android.content.Context;
import android.widget.Toast;

import com.example.news_app.models.newsapiresponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class requestmanager {
    Context context;


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getnewsheadlines(onfetchdatalistener listener,String category,String query){
        callNewsApi callNewsApi = retrofit.create(callNewsApi.class);
        Call<newsapiresponse> call = callNewsApi.callHeadlines("in" , category, query,context.getString(R.string.api_key));

        try {
            {
                call.enqueue(new Callback<newsapiresponse>() {
                    @Override
                    public void onResponse(Call<newsapiresponse> call, Response<newsapiresponse> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(context, "Error!!", Toast.LENGTH_SHORT).show();
                        }
                        listener.onfetchdata(response.body().getArticles(),response.message());
                    }

                    @Override
                    public void onFailure(Call<newsapiresponse> call, Throwable t) {
                        listener.onerror("request failed");

                    }
                });
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public requestmanager(Context context) {
        this.context = context;

    }
   public interface callNewsApi{
        @GET("top-headlines")
        Call<newsapiresponse> callHeadlines(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apiKey")  String api_Key
        );
   }
}
