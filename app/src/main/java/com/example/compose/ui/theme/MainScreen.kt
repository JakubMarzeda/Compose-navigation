package com.example.compose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    val isFormValid by derivedStateOf {
        name.isNotBlank() &&
                surname.isNotBlank() &&
                age.toIntOrNull() != null &&
                height.toFloatOrNull() != null &&
                weight.toFloatOrNull() != null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Kalkulator BMI",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )

        InputCard(
            value = name,
            onValueChange = { name = it },
            label = "Imię",
            icon = Icons.Default.Person
        )

        InputCard(
            value = surname,
            onValueChange = { surname = it },
            label = "Nazwisko",
            icon = Icons.Default.Badge
        )

        InputCard(
            value = age,
            onValueChange = { age = it },
            label = "Wiek",
            icon = Icons.Default.CalendarToday,
            keyboardType = KeyboardType.Number
        )

        InputCard(
            value = height,
            onValueChange = { height = it },
            label = "Wzrost (cm)",
            icon = Icons.Default.Height,
            keyboardType = KeyboardType.Number
        )

        InputCard(
            value = weight,
            onValueChange = { weight = it },
            label = "Waga (kg)",
            icon = Icons.Default.MonitorWeight,
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate("bmi/$name/$height/$weight")
            },
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Icon(Icons.Default.FitnessCenter, contentDescription = "BMI")
            Spacer(Modifier.width(8.dp))
            Text("Oblicz BMI")
        }

        Button(
            onClick = {
                navController.navigate("api/$name")
            },
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Icon(Icons.Default.Search, contentDescription = "API")
            Spacer(Modifier.width(8.dp))
            Text("Sprawdź płeć")
        }
    }
}

@Composable
fun InputCard(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            leadingIcon = { Icon(icon, contentDescription = null) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(navController = rememberNavController())
}
