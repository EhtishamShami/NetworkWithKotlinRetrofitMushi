package com.example.stc.networkarchitecture

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.stc.networkarchitecture.apis.dependencies.ProjectNetwork
import com.example.stc.networkarchitecture.apis.requests.createRequest
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.awaitResult

class MainActivity : AppCompatActivity() {

    protected var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        job = launch(UI) {
            try {
                getResponse()
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }


    }



    suspend fun getResponse()
    {
        val userRequest=createRequest("Android","1","5443535353533",
                "","+9665012234568",
                "xyz","xyz","",
                "trId-guid","user1@gmail.com"
        )

        val result= ProjectNetwork.ProjectNetwork.getServerAPI().createUser(userRequest).awaitResult();
        return when (result) {
            is Result.Ok -> {

                val createUsesResponse=result

                Log.d("response ", ""+createUsesResponse.toString())
                Toast.makeText(applicationContext,"Eat Sleep Code Repeat", Toast.LENGTH_SHORT).show()
            }
            is Result.Error -> throw Throwable("HTTP error: ${result.response.message()}")
            is Result.Exception -> throw result.exception
            else -> {
                throw Throwable("Something went wrong, please try again later.")
            }

        //        val result = ProjectNetwork.getNews(after, limit).awaitResult()

        }
    }
}
