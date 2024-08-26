package com.aimids.todos.models

data class TodoListItem(val id: Int, val title: String, var isCompleted: Boolean = false)
