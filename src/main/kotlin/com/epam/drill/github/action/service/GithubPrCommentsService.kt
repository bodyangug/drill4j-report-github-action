package com.epam.drill.github.action.service

import com.epam.drill.github.action.entity.GithubPrCommentResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface GithubPrCommentsService {
    @POST("/repos/{owner}/{repo}/issues/{pull_number}/comments")
    fun createComment(
        @Header("Authorization") token: String,
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path("pull_number") number: Int,
        @Body body: String
    ): Call<GithubPrCommentResponse>
}
