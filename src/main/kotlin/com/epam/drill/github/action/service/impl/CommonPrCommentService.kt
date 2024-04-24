package com.epam.drill.github.action.service.impl

import com.epam.drill.github.action.entity.GithubEvent
import com.epam.drill.github.action.entity.GithubPrComment
import com.epam.drill.github.action.logger.debug
import com.epam.drill.github.action.service.GithubPrCommentsService
import com.epam.drill.github.action.service.PrCommentService
import com.epam.drill.github.action.utils.ARGS_INDEX_EVENT_FILE_PATH
import com.epam.drill.github.action.utils.ARGS_INDEX_TOKEN
import com.epam.drill.github.action.utils.EXIT_CODE_FAILURE
import com.epam.drill.github.action.utils.EXIT_CODE_SUCCESS
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

class CommonPrCommentService : PrCommentService {

    override fun makePrComments(
        httpUrl: HttpUrl,
    ): Int {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit =
            Retrofit.Builder().baseUrl(httpUrl).addConverterFactory(MoshiConverterFactory.create(moshi)).build()
        return try {
            val event = createGithubEvent(System.getenv("GITHUB_EVENT_PATH"), moshi)
            //Send message to pr
            val comments = listOf(GithubPrComment("Hello world", event.pull_request.head.sha))
            val token = System.getenv("INPUT_REPO-TOKEN")
            makeComments(comments, token, event, retrofit)

            EXIT_CODE_SUCCESS
        } catch (ex: Throwable) {
            val errorMessage = if (ex.message.isNullOrBlank()) "Unknown error: ${ex.javaClass.name}" else ex.message
            com.epam.drill.github.action.logger.error("while making PR comments: $errorMessage")
            EXIT_CODE_FAILURE
        }
    }

    private fun makeComments(
        comments: List<GithubPrComment>, token: String, event: GithubEvent, retrofit: Retrofit
    ) {
        debug("fun makeComments: comments=$comments|event=$event")

        val githubPrCommentsService = retrofit.create(GithubPrCommentsService::class.java)
        comments.forEach { comment ->
            githubPrCommentsService.createComment(
                "token $token", event.pull_request.user.login, event.repository.name, event.pull_request.number, comment
            ).execute()
        }
    }

    private fun createGithubEvent(
        eventFilePath: String, moshi: Moshi
    ): GithubEvent {
        debug("fun createGithubEvent: $eventFilePath")

        val json = File(eventFilePath).readText()
        return moshi.adapter(GithubEvent::class.java).fromJson(json)
            ?: throw Exception("Could not create json from file: $eventFilePath")
    }

}
