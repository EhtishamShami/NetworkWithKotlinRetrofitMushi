package com.example.stc.networkarchitecture.apis.requests

/**
 * Created by Ehitshamshami on 1/3/2018.
 */

data class createRequest(var channel:String,
                         var roleId:String,
                         var enterpriseId:String,
                         var userEmail:String,
                         var contactNumber:String,
                         var userName_en:String,
                         var userName_ar:String,
                         var userProfileImage:String,
                         var client_transaction_id:String,
                         var userIdentifier:String
                         )

