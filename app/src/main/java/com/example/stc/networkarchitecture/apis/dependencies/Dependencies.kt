package com.example.stc.networkarchitecture.apis.dependencies

import android.util.Log
import com.example.stc.networkarchitecture.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Ehitshamshami on 1/3/2018.
 */

abstract class Dependencies
{


    protected fun <S> provideRestApi(classService: Class<S>, baseUrl: String?): S {
        var baseUrl = baseUrl
        val okHttpClient = provideOkHttpClientDefault(provideHttpLoggingInterceptor())
        if (baseUrl == null)
            baseUrl = getBaseUrl()
        val builder = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())

        return builder.build().create(classService)
    }

    protected fun provideOkHttpClientDefault(interceptor:HttpLoggingInterceptor): OkHttpClient {
        val okBuilder = OkHttpClient.Builder()
        okBuilder.addInterceptor(interceptor)
        okBuilder.addInterceptor { chain ->

            val request = chain.request()
            val builder = request.newBuilder()
            val headers = getHeaders()
            if (headers != null && headers.size > 0) {
                for ((key, value) in headers) {
                    builder.addHeader(key, value)
                    Log.e(key, value)
                }
            }
            chain.proceed(builder.build())
        }

        val timeout = getTimeOut()
        okBuilder.connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
        okBuilder.readTimeout(timeout.toLong(), TimeUnit.SECONDS)
        okBuilder.writeTimeout(timeout.toLong(), TimeUnit.SECONDS)

        return okBuilder.build()
    }

    protected fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }




    fun getTimeOut(): Int {
        return 30
    }


    abstract fun getBaseUrl(): String

    abstract fun getHeaders(): HashMap<String, String>




}