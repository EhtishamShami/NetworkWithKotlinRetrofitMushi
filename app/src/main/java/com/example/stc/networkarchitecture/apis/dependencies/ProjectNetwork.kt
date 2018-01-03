package com.example.stc.networkarchitecture.apis.dependencies

import java.util.*

/**
 * Created by Ehitshamshami on 1/3/2018.
 */

public class ProjectNetwork():Dependencies()
{


    companion object {
        private lateinit var serverAPI: ServerApis
        val ProjectNetwork=ProjectNetwork()
    }




    fun getServerAPI():ServerApis{

        serverAPI = provideRestApi(ServerApis::class.java, null)
        return serverAPI
    }


    override fun getBaseUrl(): String {
        return "http://124.109.32.134:8080/stc-alacarte-app/"

    }
    override fun getHeaders(): HashMap<String, String> {
        val params = HashMap<String, String>()
        params.put("Content-Type", "application/json")

        params.put("X-STC-MSISDN", "")
        return params
    }

//    public fun getServerAPI(): ServerApis {
//        return serverAPI
//    }


}

