package com.aplikasi.crud_room.rest

import com.aplikasi.crud_room.model.Response
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("push.php")
    fun pushNotif(
        @Field("title") title:String,
        @Field("body") body:String,
        @Field("token") token:String
    ): Call<Response>
}