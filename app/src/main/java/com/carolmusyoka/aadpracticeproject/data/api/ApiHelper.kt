package com.carolmusyoka.aadpracticeproject.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getLearningLeaders() = apiService.getLearningLeaders()
    suspend fun getSkillLeaders() = apiService.getSkillLeaders()
}