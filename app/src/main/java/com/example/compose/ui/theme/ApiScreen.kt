// ApiScreen.kt
package com.example.compose.ui

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.data.ApiService
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ApiScreen(navController: NavController, name: String) {
    var genderInfo by remember { mutableStateOf("Ładowanie...") }

    LaunchedEffect(name) {
        genderInfo = ApiService.getGenderInfo(name) ?: "Błąd pobierania danych"
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Imię: $name")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Wynik: $genderInfo")

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Cofnij do ekranu głównego")
        }
    }
}

@Preview
@Composable
fun PreviewApiScreen() {
    ApiScreen(navController = rememberNavController(), name = "Jan")
}
