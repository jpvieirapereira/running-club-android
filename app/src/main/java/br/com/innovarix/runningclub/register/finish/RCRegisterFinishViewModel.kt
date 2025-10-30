package br.com.innovarix.runningclub.register.finish

import br.com.innovarix.runningclub.core.base.ViewModel
import br.com.innovarix.runningclub.core.extensions.orZero
import br.com.innovarix.runningclub.core.extensions.unMask
import br.com.innovarix.runningclub.register.domain.RCLevelType
import br.com.innovarix.runningclub.register.domain.RCOptionModel
import br.com.innovarix.runningclub.register.finish.RCRegisterFinishInputType.*
import br.com.innovarix.runningclub.register.lead.RCRegisterInputType

class RCRegisterFinishViewModel : ViewModel<
        RCRegisterFinishUiState,
        RCRegisterFinishUiEvent,
        RCRegisterFinishUiAction
        >(RCRegisterFinishUiState()) {

    override fun dispatchAction(action: RCRegisterFinishUiAction) {
        when (action) {
            RCRegisterFinishUiAction.OnInit -> onInit()
            RCRegisterFinishUiAction.OnTollbarClicked -> onToolbarClicked()
            is RCRegisterFinishUiAction.OnChangeFields -> onChangeField(action.data)
            RCRegisterFinishUiAction.OnAdvanceClicked -> onAdvanceClicked()
        }
    }

    private fun onInit() {

        val optionSurname = RCRegisterFinishUiModel(
            type = SURNAME
        )

        val levelsType = RCLevelType.entries.mapIndexed { index, rcLevelType ->
            RCOptionModel(id = index.toString(), option = rcLevelType.level)
        }

        val optionLevel = RCRegisterFinishUiModel(
            optionSelected = levelsType.firstOrNull(),
            options = levelsType,
            type = LEVEL
        )

        val availability = (1..7).map {
            RCOptionModel(id = it.toString(), option = "$it x Semana")
        }

        val optionAvailability = RCRegisterFinishUiModel(
            optionSelected = availability.firstOrNull(),
            options = availability,
            type = AVAILABILITY
        )

        val challenges = listOf(5, 10, 21, 42).map {
            RCOptionModel(id = it.toString(), option = "$it km")
        }

        val optionChallenge = RCRegisterFinishUiModel(
            optionSelected = challenges.firstOrNull(),
            options = challenges,
            type = CHALLENGE
        )

        val optionTerms = RCRegisterFinishUiModel(
            type = TERMS
        )

        val register = listOf(
            optionSurname,
            optionLevel,
            optionAvailability,
            optionChallenge,
            optionTerms
        )

        updateState {
            it.copy(options = register)
        }
    }

    private fun onChangeField(data: RCRegisterFinishUiModel) {
        val currentState = state.value.options

        val newList = currentState.map { field ->
            return@map when (field.type) {
                SURNAME -> {
                    if (data.surName?.length.orZero() <= RCRegisterInputType.NAME.maxLength) {
                        field.copy(surName = data.surName)
                    } else {
                        field
                    }
                }

                LEVEL, CHALLENGE, AVAILABILITY -> {
                    field.copy(optionSelected = data.optionSelected)
                }

                TERMS -> {
                    field.copy(termsChecked = data.termsChecked)
                }
            }
        }

        val isEnableButton = newList.firstOrNull { field ->
            field.type == TERMS
        }?.termsChecked ?: false

        updateState { it.copy(options = newList, enableButton = isEnableButton) }
    }

    private fun onToolbarClicked() {

    }

    private fun onAdvanceClicked() {
        val currentState = state.value.options

    }
}