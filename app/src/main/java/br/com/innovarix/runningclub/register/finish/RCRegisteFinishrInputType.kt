package br.com.innovarix.runningclub.register.finish

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import br.com.innovarix.runningclub.R
import br.com.innovarix.runningclub.core_theme.components.input.RCMaskVisualTransformation

enum class RCRegisterFinishInputType(@StringRes val label: Int) {
    SURNAME(R.string.rc_register_finish_surname),
    LEVEL(R.string.rc_register_finish_level),
    AVAILABILITY(R.string.rc_register_finish_availability_training),
    CHALLENGE(R.string.rc_register_finish_challenge),
    TERMS(R.string.rc_register_finish_terms)
}