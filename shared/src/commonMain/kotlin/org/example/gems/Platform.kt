package org.example.gems

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform