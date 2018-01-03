package com.example.stc.networkarchitecture.apis.dependencies

import com.example.stc.networkarchitecture.apis.requests.createRequest
import com.example.stc.networkarchitecture.apis.responses.createUsesResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Ehitshamshami on 1/3/2018.
 */

public interface ServerApis
{

    @POST("user/create")
    fun createUser(@Body createRequest: createRequest): Call<createUsesResponse>


}