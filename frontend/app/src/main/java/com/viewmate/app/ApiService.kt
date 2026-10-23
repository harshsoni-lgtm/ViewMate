package com.viewmate.app

import retrofit2.http.Body
import retrofit2.http.POST

data class RecommendationRequest(
    val mood: String
)

data class RecommendationResponse(
    val mood: String,
    val recommendations: List<String>
)

interface ApiService {

    @POST("recommend")
    suspend fun getRecommendations(
        @Body request: RecommendationRequest
    ): RecommendationResponse
}