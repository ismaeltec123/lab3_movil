package com.quispe.ismael.poketinder2024_2_d

import retrofit2.Response
import retrofit2.http.GET
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface PokemonApi {
    @GET("/api/v2/pokemon")
    suspend fun getPokemons():Response<PokemonListResponse>
    private fun getRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("NAME-HEADER", "VALUE-HEADER")
                .build()
            chain.proceed(newRequest)
        }

        val client = httpClient.build()

        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

}
