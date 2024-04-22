import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val result = makePrComments(args)
    if (result != 0) {
        exitProcess(result)
    }
    println("Comments were made!")
}
