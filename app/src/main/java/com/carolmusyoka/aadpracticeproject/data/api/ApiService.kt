package com.carolmusyoka.aadpracticeproject.data.api

import com.carolmusyoka.aadpracticeproject.data.model.LearningHoursItem
import retrofit2.http.GET

interface ApiService {
    @GET("api/hours")
    suspend fun getLearningLeaders(): List<LearningHoursItem>
}