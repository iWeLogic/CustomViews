package com.iwelogic.custom.views.ui.screens.chart

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnrememberedMutableState")
@Composable
fun ChartScreen() {


    Column(modifier = Modifier.fillMaxSize()) {

        val values = remember {
            mutableStateOf(generateValues())
        }

        Button(modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(), onClick = {
            values.value = generateValues()
        }) {
            Text(text = "GENERATE")
        }

        Spacer(modifier = Modifier.height(100.dp))

        Chart(
            Modifier
                .aspectRatio(1f)
                .fillMaxSize(),
            values.value

        )
    }
}

fun generateValues(): List<ValueContainer> {
    val temp = mutableListOf<ValueContainer>()
    for (i in 0..8) {
        temp.add(ValueContainer(y = (0..1000).random().toFloat() / 10f, x = i * 3))
    }
    return temp
}




