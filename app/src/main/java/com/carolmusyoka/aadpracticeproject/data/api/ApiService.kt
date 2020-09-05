package com.carolmusyoka.aadpracticeproject.data.api

import com.carolmusyoka.aadpracticeproject.data.model.LearningHoursItem
import com.carolmusyoka.aadpracticeproject.data.model.SkillItem
import retrofit2.http.GET

interface ApiService {
    @GET("api/hours")
    suspend fun getLearningLeaders(): List<LearningHoursItem>

    @GET("api/skilliq")
    suspend fun getSkillLeaders(): List<SkillItem>
}