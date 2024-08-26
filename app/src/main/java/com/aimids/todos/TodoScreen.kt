package com.aimids.todos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aimids.todos.models.TodoListItem

@Composable
fun TodoScreen(innerPadding: PaddingValues) {
    val todoViewModel: TodoViewModel = hiltViewModel()
    val todoItems by todoViewModel.todoLiveData.observeAsState(emptyList())

    LaunchedEffect(key1 = true) {
        todoViewModel.getTodos()
    }

    Column(modifier = Modifier.padding(innerPadding)) {

        LazyColumn {
            items(todoItems) { item ->
                TodoItemView(
                    item = item,
                    onUpdateItem = { todoViewModel.markCompleteTodo(it) },
                    onDeleteItem = { todoViewModel.removeTodo(it) }
                )
            }

        }

        Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            var text by remember {
                mutableStateOf("")
            }
            TextField(value = text, onValueChange = { text = it },
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp)),
                label = { Text("New Task") })

            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = {
                if (text.isNotBlank()) {
                    todoViewModel.addTodoItem(text)
                    text = ""
                }
            }) {
                Text("Add")
            }
        }
    }
}


@Preview
@Composable
fun PreviewTopScreen() {
    val paddingValues = PaddingValues(12.dp)
    TodoScreen(innerPadding = paddingValues)
}
