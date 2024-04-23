package com.epam.drill.github.action.logger

fun debug(message: String) {
    println("::debug::$message")
}

fun error(message: String) {
    println("::error::$message")
}
