package cl.mess.pokeinfo.utils

import kotlin.random.Random

object RandomFactory {
    fun generateRandomString(length: Int = 9): String {
        val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { Random.nextInt(from = 0, until = charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    fun generateRandomInt(range: IntRange = Int.MIN_VALUE..Int.MAX_VALUE): Int {
        return Random.nextInt(range.first, range.last)
    }

    fun generateRandomBoolean(): Boolean {
        return Random.nextBoolean()
    }
}
