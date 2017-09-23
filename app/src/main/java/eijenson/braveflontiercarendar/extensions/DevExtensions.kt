package eijenson.braveflontiercarendar.extensions

/**
 * 開発用ツール
 */
var time: Long = 0

fun rap() {
    rap("")
}

fun rap(message: String) {
    if (time != 0L) println(message + ":時間" + (System.currentTimeMillis() - time))
    time = System.currentTimeMillis()
}


fun reset() {
    time = System.currentTimeMillis()
    println("時間リセット")
}