package com.epam.drill.github.action


fun main() {
    val whoToGreet = System.getenv("INPUT_WHO-TO-GREET") ?: "empty-who-to-greet"
    println("whoToGreet = $whoToGreet")
    val eventPath = System.getenv("GITHUB_EVENT_PATH") ?: "empty-event"
    println("eventPath = $eventPath")
    println("Comments were made!")
//    val commentService = CommonPrCommentService()
//    val result = commentService.makePrComments(args, HttpUrl.get(URL_GITHUB))
//    if (result != 0) {
//        exitProcess(result)
//    }
}
