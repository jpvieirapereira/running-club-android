package br.com.innovarix.runningclub.register.lead

import br.com.innovarix.runningclub.core.base.ViewModel
import br.com.innovarix.runningclub.core.extensions.isValidBirthdate
import br.com.innovarix.runningclub.core.extensions.isValidCpf
import br.com.innovarix.runningclub.core.extensions.isValidEmail
import br.com.innovarix.runningclub.core.extensions.isValidPhoneNumber
import br.com.innovarix.runningclub.core.extensions.unMask
import br.com.innovarix.runningclub.register.domain.RCRegisterModel
import br.com.innovarix.runningclub.register.lead.RCRegisterInputType.BIRTH_DATE
import br.com.innovarix.runningclub.register.lead.RCRegisterInputType.CPF
import br.com.innovarix.runningclub.register.lead.RCRegisterInputType.E_MAIL
import br.com.innovarix.runningclub.register.lead.RCRegisterInputType.NAME
import br.com.innovarix.runningclub.register.lead.RCRegisterInputType.PASSWORD
import br.com.innovarix.runningclub.register.lead.RCRegisterInputType.PHONE_NUMBER

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
            val isValid = when (field.type) {
                CPF -> field.value.length == field.type.maxLength
                BIRTH_DATE -> field.value.length == field.type.maxLength
                E_MAIL -> field.value.length > field.type.minLength
                NAME -> field.value.length > field.type.minLength
                PASSWORD -> field.value.length >= field.type.minLength
                PHONE_NUMBER -> field.value.length == field.type.maxLength
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
            when (field.type) {
                CPF -> field.copy(isValidationError = !field.value.isValidCpf())
                E_MAIL -> field.copy(isValidationError = !field.value.isValidEmail())
                BIRTH_DATE -> field.copy(isValidationError = !field.value.isValidBirthdate())
                PHONE_NUMBER -> field.copy(isValidationError = !field.value.isValidPhoneNumber())
                else -> field
            }
        }

        if (newList.map { !it.isValidationError }.all { it }) {
            val register = RCRegisterModel(
                fullName = getValueOfField(NAME),
                documentNumber = getValueOfField(CPF),
                birthDate = getValueOfField(BIRTH_DATE),
                email = getValueOfField(E_MAIL),
                phone = getValueOfField(PHONE_NUMBER),
                password = getValueOfField(PASSWORD),
            )
            sendEvent { RCRegisterUiEvent.NavigateToFinish(register) }
        } else {
            updateState { it.copy(fields = newList) }
        }
    }

    private fun getValueOfField(type: RCRegisterInputType): String {
        return state.value.fields.firstOrNull { it.type == type }?.value.orEmpty()
    }
}