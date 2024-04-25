package com.epam.drill.github.action.service.impl

import com.epam.drill.github.action.entity.GithubEvent
import com.epam.drill.github.action.logger.debug
import com.epam.drill.github.action.service.GithubPrCommentsService
import com.epam.drill.github.action.service.PrCommentService
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
            println("Create message")
            val comments = listOf("Hello from Github Action")
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
        comments: List<String>, token: String, event: GithubEvent, retrofit: Retrofit
    ) {
        debug("fun makeComments: comments=$comments|event=$event")

        val githubPrCommentsService = retrofit.create(GithubPrCommentsService::class.java)
        comments.forEach { comment ->
            println("Send comment with params:")
            println("token: $token")
            println("user.login: ${event.pull_request.user.login}")
            println("repo.name: ${event.repository.name}")
            println("pr number: ${event.pull_request.number}")
            println("comment: $comment")
            val execute = githubPrCommentsService.createComment(
                token = "token $token",
                owner = event.pull_request.user.login,
                repo = event.repository.name,
                number = event.pull_request.number,
                body = comment
            ).execute()
            execute.let {
                println("Response code: ${it.code()} body: ${it.body()}")
            }
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
