package com.iwelogic.custom.views.ui.screens.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PickerScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        val selected = remember {
            mutableStateOf("Red")
        }

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(30.dp),
            text = selected.value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .padding(30.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                onClick = {
                    selected.value = "Green"
                }) {
                Text(
                    text = "Green",
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }

            Button(
                modifier = Modifier
                    .padding(30.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                onClick = {
                    selected.value = "Blue"
                }) {
                Text(
                    text = "Blue",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }

        Picker(
            Modifier
                .height(250.dp)
                .width(150.dp)
                .align(Alignment.CenterHorizontally),
            values = listOf("Yellow", "Red", "Green", "Black", "Gray", "Blue", "Orange"),
            value = selected.value,
            onValueChanged = {
                selected.value = it
            })
    }
}




