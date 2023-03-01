package com.iwelogic.custom.views.ui.screens.picker

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Picker(modifier: Modifier, values: List<String>, value: String, onValueChanged: (String) -> Unit) {

    val valuesWithEmptyFields = values.toMutableList().apply {
        add(0, "")
        add(0, "")
        add("")
        add("")
    }

    val scope = rememberCoroutineScope()

    var isLoaded by remember { mutableStateOf(false) }

    var currentValue by remember { mutableStateOf(value) }
    val scrollState = rememberLazyListState(initialFirstVisibleItemIndex = valuesWithEmptyFields.indexOf(value) - 2)

    var columnHeightPx by remember { mutableStateOf(0f) }
    var columnHeightDp by remember { mutableStateOf(250.dp) }

    val localDensity = LocalDensity.current

    LaunchedEffect(key1 = true) {
        isLoaded = true
    }
    LaunchedEffect(key1 = value) {
        scope.launch {
            scrollState.animateScrollToItem(valuesWithEmptyFields.indexOf(value) - 2)
        }
    }
    Column(modifier = modifier) {
        Box(
            modifier = modifier.onGloballyPositioned { coordinates ->
                columnHeightPx = coordinates.size.height.toFloat()
                columnHeightDp = with(localDensity) { coordinates.size.height.toDp() }
            }
        ) {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                state = scrollState,
                flingBehavior = rememberSnapFlingBehavior(lazyListState = scrollState)
            ) {
                items(valuesWithEmptyFields) { item ->
                    Item(text = item, columnHeightPx, columnHeightDp / 5) {
                        if (isLoaded && currentValue != it) {
                            currentValue = it
                            onValueChanged(it)
                        }
                    }
                }
            }

            Divider(Modifier.padding(top = columnHeightDp / 5 * 2 - 1.dp), color = Color.LightGray, thickness = 2.dp)

            Divider(Modifier.padding(top = columnHeightDp / 5 * 3 - 1.dp), color = Color.LightGray, thickness = 2.dp)
        }
    }

}

@Composable
fun Item(text: String, height: Float, itemHeightDp: Dp, onItemSelected: (String) -> Unit) {
    var positionInRootTopBar by remember { mutableStateOf(0.0f) }
    val centerHeight = height / 2.0f
    val delta = abs(centerHeight - positionInRootTopBar)
    if (delta < 10.0f && height > 0.0f) {
        onItemSelected(text)
    }

    Box(
        modifier = Modifier
            .height(itemHeightDp)
            .onGloballyPositioned { coordinates ->
                positionInRootTopBar = coordinates.positionInParent().y + coordinates.size.height.toFloat() / 2
            }
            .graphicsLayer {
                scaleX = 1.0f - (delta / height)
                scaleY = 1.0f - (delta / height)
                alpha = 1.0f - (delta / (height / 2.0f))
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            color = Color.Black,
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )
    }
}