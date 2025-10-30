package br.com.innovarix.runningclub.register.finish

import br.com.innovarix.runningclub.register.domain.RCOptionModel

data class RCRegisterFinishUiModel(
    val type: RCRegisterFinishInputType,
    val surName: String? = null,
    val optionSelected: RCOptionModel? = null,
    val options: List<RCOptionModel> = emptyList(),
    val termsChecked: Boolean = false
)