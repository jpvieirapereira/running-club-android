package br.com.innovarix.runningclub.register

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import br.com.innovarix.runningclub.R
import br.com.innovarix.runningclub.core_theme.components.input.RCMaskVisualTransformation

private const val DATE_MASK = "##/##/####"
private val CPF_MASK = "###.###.###-##"
private val PHONE_MASK = "(##) # ####-####"

enum class RCRegisterInputType(
    @StringRes val label: Int,
    @StringRes val validationError: Int,
    val maskTransformation: VisualTransformation? = null,
    val keyboardOptions: KeyboardOptions,
    val maxLength: Int = 80
) {
    NAME(
        R.string.rc_register_label_name,
        R.string.rc_register_error_name,
        null,
        KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
    ),
    CPF(
        R.string.rc_register_label_cpf,
        R.string.rc_register_error_cpf,
        RCMaskVisualTransformation(CPF_MASK),
        KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        11
    ),
    BIRTH_DATE(
        R.string.rc_register_label_birthdate,
        R.string.rc_register_error_birthdate,
        RCMaskVisualTransformation(DATE_MASK),
        KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        8
    ),
    E_MAIL(
        R.string.rc_register_label_email,
        R.string.rc_register_error_email,
        null,
        KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        50
    ),
    PHONE_NUMBER(
        R.string.rc_register_label_phone,
        R.string.rc_register_error_phone,
        RCMaskVisualTransformation(PHONE_MASK),
        KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        11
    ),
    PASSWORD(
        R.string.rc_register_label_password,
        R.string.rc_register_error_password,
        PasswordVisualTransformation(),
        KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        20
    )
}