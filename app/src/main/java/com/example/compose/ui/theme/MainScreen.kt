package com.example.compose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    var gender by remember { mutableStateOf("") }
    var isFormValid by remember { mutableStateOf(false) }

    fun validateForm() {
        isFormValid = name.isNotBlank() && surname.isNotBlank() &&
                age.toIntOrNull() != null && height.toFloatOrNull() != null && weight.toFloatOrNull() != null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = name, onValueChange = {
            name = it; validateForm()
        }, label = { Text("Imię") })
        OutlinedTextField(value = surname, onValueChange = {
            surname = it; validateForm()
        }, label = { Text("Nazwisko") })
        OutlinedTextField(value = age, onValueChange = {
            age = it; validateForm()
        }, label = { Text("Wiek") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        OutlinedTextField(value = height, onValueChange = {
            height = it; validateForm()
        }, label = { Text("Wzrost (cm)") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        OutlinedTextField(value = weight, onValueChange = {
            weight = it; validateForm()
        }, label = { Text("Waga (kg)") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate("bmi/$name/$height/$weight")
            },
            enabled = isFormValid
        ) {
            Text("Oblicz BMI")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                navController.navigate("api/$name")
            },
            enabled = isFormValid
        ) {
            Text("Sprawdź płeć")
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen(navController = rememberNavController())
}
