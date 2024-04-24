package com.epam.drill.github.action

import com.epam.drill.github.action.service.impl.CommonPrCommentService
import com.epam.drill.github.action.utils.URL_GITHUB
import okhttp3.HttpUrl
import kotlin.system.exitProcess


fun main() {
    val whoToGreet = System.getenv("INPUT_WHO-TO-GREET") ?: "empty-who-to-greet"
    val commentService = CommonPrCommentService()
    println("whoToGreet = $whoToGreet")
    val result = commentService.makePrComments(HttpUrl.get(URL_GITHUB))
    if (result != 0) {
        exitProcess(result)
    }
    println("Comments were made!")
}
