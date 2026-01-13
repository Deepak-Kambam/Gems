package org.example.gems.repository

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.example.gems.network.httpClient
import org.example.gems.model.LoginRequest

// USE 10.0.2.2 for Android Emulator!
// If using a Real Device: You must use your computer's Wi-Fi IP address (e.g., http://192.168.1.5:8080)
const val BASE_URL = "http://10.0.2.2:8080" 

class AuthRepository {
    suspend fun login(studentId: String): String {
        val response = httpClient.post("$BASE_URL/api/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(studentId))
        }
        return response.body()
    }
}
