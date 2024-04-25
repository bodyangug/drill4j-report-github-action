package com.epam.drill.github.action.service

import com.epam.drill.github.action.entity.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface GithubPrCommentsService {
    @POST("/repos/{owner}/{repo}/issues/{issue_number}/comments")
    fun createComment(
        @Header("Authorization") token: String,
        @Header("Accept") accept: String = "application/vnd.github+json",
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path("issue_number") issueNumber: Int,
        @Body body: RequestBody
    ): Call<ResponseBody>
}
