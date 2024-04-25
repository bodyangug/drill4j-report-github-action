package com.epam.drill.github.action

import com.epam.drill.github.action.entity.GithubEvent
import com.epam.drill.github.action.service.impl.CommonPrCommentService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import mu.KotlinLogging
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

class MainFacade {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val retrofit =
        Retrofit.Builder()
            .baseUrl(HttpUrl.get(URL_GITHUB))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    private val commentService = CommonPrCommentService(retrofit)
    private val logger = KotlinLogging.logger {}

    fun doReport() {
        logger.debug { "Starting doReport()." }
        try {
            //1. Get all params to do report
            val event = createGithubEvent(System.getenv("GITHUB_EVENT_PATH"))
            val token = System.getenv("INPUT_REPO-TOKEN")
            val whoToGreet = System.getenv("INPUT_WHO-TO-GREET")
            //2. Generate comment report
            val comment = "Hello from Github Action. who-to-greet param: $whoToGreet"
            //3. Send comment to pr
            commentService.sendComment(comment, token, event)
        } catch (ex: Throwable) {
            val errorMessage = if (ex.message.isNullOrBlank()) "Unknown error: ${ex.javaClass.name}" else ex.message
            logger.error { "While making PR comments: $errorMessage." }
        }
    }

    private fun createGithubEvent(
        eventFilePath: String
    ): GithubEvent {
        logger.info { "Creating Github Event file." }
        return moshi.adapter(GithubEvent::class.java).fromJson(File(eventFilePath).readText())
            ?: throw Exception("Could not create json from file: $eventFilePath")
    }
}
