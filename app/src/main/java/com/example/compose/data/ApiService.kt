package com.example.compose.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.net.HttpURLConnection
import java.net.URL

@Serializable
data class GenderResponse(val name: String, val gender: String?, val probability: Double)

object ApiService {
    fun getGenderInfo(name: String): String? {
        val url = URL("https://api.genderize.io/?name=$name")
        return try {
            with(url.openConnection() as HttpURLConnection) {
                val response = inputStream.bufferedReader().use { it.readText() }
                val result = Json.decodeFromString<GenderResponse>(response)
                result.gender?.let { "Płeć: $it (pewność: ${(result.probability * 100).toInt()}%)" }
            }
        } catch (e: Exception) {
            "Błąd połączenia"
        }
    }
}
