package br.com.innovarix.runningclub.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface UiState

interface UiEvent

interface UiAction

abstract class ViewModel<state : UiState, event : UiEvent, action : UiAction>(
    initialState: state
) : ViewModel() {

    private val _state: MutableStateFlow<state> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _event = Channel<event>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    abstract fun dispatchAction(action: action)

    protected fun updateState(newState: (state) -> state) {
        _state.update(newState)
    }

    protected fun sendEvent(newEvent: () -> event) {
        viewModelScope.launch {
            _event.send(newEvent())
        }
    }
}