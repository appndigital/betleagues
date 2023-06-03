package com.appndigital.betleagues.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun <T> AutoCompleteTextView(
    modifier: Modifier,
    query: String,
    queryLabel: String,
    onQueryChanged: (String) -> Unit = {},
    predictions: List<T>,
    onDoneActionClick: () -> Unit = {},
    onClearClick: () -> Unit = {},
    onItemClick: (T) -> Unit = {},
    itemContent: @Composable (T) -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val lazyListState = rememberLazyListState()

    val minHeight = 56.dp

    LazyColumn(
        state = lazyListState,
        modifier = modifier.heightIn(max = minHeight * 6)
    ) {
        item {
            QuerySearch(
                query = query,
                label = queryLabel,
                onQueryChanged = onQueryChanged,
                onDoneActionClick = {
                    onDoneActionClick()
                },
                onClearClick = {
                    onClearClick()
                }
            )
        }
        if (predictions.isNotEmpty()) {
            items(predictions) { prediction ->
                Row(
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            focusManager.clearFocus()
                            keyboardController?.hide()
                            onItemClick(prediction)
                        }
                ) {
                    itemContent(prediction)
                }
            }
        }
    }
}