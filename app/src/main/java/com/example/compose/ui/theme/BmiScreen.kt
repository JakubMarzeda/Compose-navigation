package com.example.compose.ui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.data.BmiCalculator
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun BmiScreen(navController: NavController, name: String, height: String, weight: String) {
    val bmi = remember {
        BmiCalculator.calculateBmi(weight.toFloatOrNull() ?: 0f, height.toFloatOrNull() ?: 0f)
    }

    val message = when {
        bmi < 18.5 -> "Hej $name, jesteś poniżej normy. Twoje BMI wynosi $bmi."
        bmi in 18.5..24.9 -> "Hej $name, jesteś w świetnej formie! Twoje BMI wynosi $bmi."
        else -> "Hej $name, Twoje BMI wynosi $bmi, warto zadbać o zdrowie!"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message)

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Cofnij do ekranu głównego")
        }
    }
}

@Preview
@Composable
fun PreviewBmiScreen() {
    BmiScreen(navController = rememberNavController(), name = "Jan", height = "180", weight = "75")
}
