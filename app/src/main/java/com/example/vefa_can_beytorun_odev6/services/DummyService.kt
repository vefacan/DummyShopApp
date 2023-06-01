package com.example.vefa_can_beytorun_odev6.services

import com.example.vefa_can_beytorun_odev6.models.*
import retrofit2.Call
import retrofit2.http.*


interface DummyService {

    @POST("/auth/login")
    fun login( @Body jwtUser: JWTUser): Call<JWTData>

    @GET("products")
    fun products() : Call<DummyProducts>

    @GET("products")
    fun getProducts(@Query("limit") limit: Int): Call<DummyProducts>

    @GET("products/search")
    fun searchProducts(@Query("q") query: String): Call<DummyProducts>

}
