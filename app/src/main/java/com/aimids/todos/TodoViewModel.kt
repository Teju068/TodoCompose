package com.aimids.todos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aimids.todos.models.TodoListItem
import com.aimids.todos.repositories.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val todoRepository: ToDoRepository) : ViewModel() {
    private val _mutableTodoListItem = MutableLiveData<List<TodoListItem>>()
    val todoLiveData: LiveData<List<TodoListItem>> = _mutableTodoListItem

    fun addTodoItem(text: String) {
        todoRepository.addTodoItem(text)
        updateTodoList()
    }

    fun removeTodo(todoListItem: TodoListItem) {
        todoRepository.removeTodoItem(todoListItem)
        updateTodoList()
    }

    fun markCompleteTodo(todoListItem: TodoListItem) {
        todoRepository.markCompleteItem(todoListItem)
        updateTodoList()
    }

    fun getTodos() {
        updateTodoList()
    }

    private fun updateTodoList() {
        _mutableTodoListItem.value = todoRepository.getTodosItems().toList()
    }
}