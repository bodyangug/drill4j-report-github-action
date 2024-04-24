package com.epam.drill.github.action

import com.epam.drill.github.action.logger.debug

fun main() {
    val whoToGreet = System.getenv("INPUT_WHO-TO-GREET")
    debug("WhoToGreet = $whoToGreet")
    val eventPath = System.getenv("GITHUB_EVENT_PATH")
    debug("eventPath = $eventPath")
    println("Comments were made!")
//    val commentService = CommonPrCommentService()
//    val result = commentService.makePrComments(args, HttpUrl.get(URL_GITHUB))
//    if (result != 0) {
//        exitProcess(result)
//    }
}
