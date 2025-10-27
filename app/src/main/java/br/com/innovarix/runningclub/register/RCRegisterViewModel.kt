package br.com.innovarix.runningclub.register

import android.util.Log
import androidx.compose.ui.Modifier
import br.com.innovarix.runningclub.core.base.ViewModel
import br.com.innovarix.runningclub.core.extensions.isValidBirthdate
import br.com.innovarix.runningclub.core.extensions.isValidCpf
import br.com.innovarix.runningclub.core.extensions.isValidEmail
import br.com.innovarix.runningclub.core.extensions.isValidPhoneNumber
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

        val newList = currentState.map { field ->
            if (field.type == data.type && data.value.unMask().length <= data.type.maxLength) {
                field.copy(value = data.value, isValidationError = false)
            } else {
                field
            }
        }

        val isEnableButton = newList.map { field ->
            val isValid = when(field.type) {
                RCRegisterInputType.CPF -> field.value.length == field.type.maxLength
                RCRegisterInputType.BIRTH_DATE -> field.value.length == field.type.maxLength
                RCRegisterInputType.E_MAIL -> field.value.length > field.type.minLength
                RCRegisterInputType.NAME -> field.value.length > field.type.minLength
                RCRegisterInputType.PASSWORD -> field.value.length >= field.type.minLength
                RCRegisterInputType.PHONE_NUMBER -> field.value.length == field.type.maxLength
            }
            isValid
        }.all { it }

        updateState { it.copy(fields = newList, enableButton = isEnableButton) }
    }

    private fun onToolbarClicked() {

    }

    private fun onAdvanceClicked() {
        val currentState = state.value.fields

        val newList = currentState.map { field ->
             when(field.type) {
                RCRegisterInputType.CPF -> field.copy(isValidationError = !field.value.isValidCpf())
                RCRegisterInputType.E_MAIL -> field.copy(isValidationError = !field.value.isValidEmail())
                RCRegisterInputType.BIRTH_DATE -> field.copy(isValidationError = !field.value.isValidBirthdate())
                RCRegisterInputType.PHONE_NUMBER -> field.copy(isValidationError = !field.value.isValidPhoneNumber())
                else -> field
            }
        }

        if(newList.map { !it.isValidationError }.all { it }) {
            Log.i("TAG", "Fields: ${state.value.fields}")
        } else {
            updateState { it.copy(fields = newList) }
        }
    }
}