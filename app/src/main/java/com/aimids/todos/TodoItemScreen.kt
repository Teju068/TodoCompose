package com.aimids.todos

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.aimids.todos.models.TodoListItem

@Composable
fun TodoItemView(
    item: TodoListItem,
    onUpdateItem: (TodoListItem) -> Unit,
    onDeleteItem: (TodoListItem) -> Unit
) {
    var isChecked by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                isChecked = it
                onUpdateItem(item.copy(isCompleted = it))
            }
        )
        Text(
            text = item.title,
            modifier = Modifier.weight(1f),
            style = if (item.isCompleted) TextStyle(textDecoration = TextDecoration.LineThrough) else TextStyle()
        )
        IconButton(onClick = { onDeleteItem(item) }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Task")
        }
    }
}