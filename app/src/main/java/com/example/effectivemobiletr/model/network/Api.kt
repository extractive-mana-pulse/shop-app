package com.example.effectivemobiletr.model.network

import com.example.effectivemobiletr.model.Main
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("v3/97e721a7-0a66-4cae-b445-83cc0bcf9010")
    suspend fun getAllItems() : Response<Main>

}