package com.epam.drill.github.action

import com.epam.drill.github.action.logger.debug
import com.epam.drill.github.action.service.impl.CommonPrCommentService
import com.epam.drill.github.action.utils.URL_GITHUB
import okhttp3.HttpUrl
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val commentService = CommonPrCommentService()
    val whoToGreet = args[1]
    debug("WhoToGreet = $whoToGreet")
    val result = commentService.makePrComments(args, HttpUrl.get(URL_GITHUB))
    if (result != 0) {
        exitProcess(result)
    }

    println("Comments were made!")
}
