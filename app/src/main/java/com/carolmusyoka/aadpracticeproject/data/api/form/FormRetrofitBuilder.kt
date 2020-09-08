package com.carolmusyoka.aadpracticeproject.data.api.form

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FormRetrofitBuilder {
    private const val BASE_URL = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val formApiService = getRetrofit().create(FormApiInterface::class.java)
}