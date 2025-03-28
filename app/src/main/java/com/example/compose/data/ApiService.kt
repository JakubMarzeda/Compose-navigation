package com.example.compose.data

import java.net.HttpURLConnection
import java.net.URL

object ApiService {
    fun getGenderInfo(name: String): String? {
        val url = URL("https://api.genderize.io/?name=$name")
        return try {
            with(url.openConnection() as HttpURLConnection) {
                inputStream.bufferedReader().use { it.readText() }
            }
        } catch (e: Exception) {
            null
        }
    }
}
