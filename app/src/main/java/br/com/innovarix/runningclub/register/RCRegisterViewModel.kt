package br.com.innovarix.runningclub.register

import android.util.Log
import br.com.innovarix.runningclub.core.base.ViewModel
import br.com.innovarix.runningclub.core.extensions.unMask

class RCRegisterViewModel : ViewModel<
        RCRegisterUiState,
        RCRegisterUiEvent,
        RCRegisterUiAction
        >(RCRegisterUiState()) {

    override fun dispatchAction(action: RCRegisterUiAction) {
        when (action) {
            RCRegisterUiAction.OnTollbarClicked -> onToolbarClicked()
            is RCRegisterUiAction.OnChangeFields -> onChangeField(action.data)
            RCRegisterUiAction.OnAdvanceClicked -> onAdvanceClicked()
        }
    }

    private fun onChangeField(data: RCRegisterUiModel) {
        val currentState = state.value.fields

        val newList = currentState.map {
            if (it.type == data.type && data.value.unMask().length <= data.type.maxLength) {
                it.copy(value = data.value)
            } else {
                it
            }
        }
        updateState { it.copy(fields = newList, enableButton = true) }
    }

    private fun onToolbarClicked() {

    }

    private fun onAdvanceClicked() {
        Log.i("TAG", "Fields: ${state.value.fields}")
    }
}