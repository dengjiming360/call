package com.example.wubi;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {
        @Headers({
                "cache-control:no-cache",
                "Postman-Token:9f06920b-674f-489e-a3fb-9f63a29dfac1",
                "User-Agent:PostmanRuntime/7.6.0",
                "Accept:*/*",
                "Host:api.avatardata.cn"

        })

        @GET("LookUp")
        Observable<ResponseBody> getHanzi(@Query("key")String key,@Query("content")String content);



}
