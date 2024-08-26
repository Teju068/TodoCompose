package com.aimids.todos.repositories

import com.aimids.todos.models.TodoListItem
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

class ToDoRepository @Inject constructor() {
    private var id = AtomicInteger(1);
    private val todos = mutableListOf<TodoListItem>()

    fun addTodoItem(text: String) {
        todos.add(TodoListItem(id.getAndIncrement(), text))
    }

    fun removeTodoItem(todoListItem: TodoListItem) {
        todos.removeIf { it.id == todoListItem.id }
    }

    fun markCompleteItem(todoListItem: TodoListItem) {
        val todoItem = todos.find { it.id == todoListItem.id }
        if (todoItem != null) {
            todoItem.isCompleted = todoListItem.isCompleted
        }
    }

    fun getTodosItems(): List<TodoListItem> = todos
}