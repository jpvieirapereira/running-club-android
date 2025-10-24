package br.com.innovarix.runningclub.register

import androidx.annotation.StringRes
import br.com.innovarix.runningclub.R

enum class RCRegisterInputTypes(@StringRes val label: Int) {
    NAME(R.string.rc_register_label_name),
    CPF(R.string.rc_register_label_cpf),
    BIRTH_DATE(R.string.rc_register_label_birthdate),
    E_MAIL(R.string.rc_register_label_email),
    PHONE_NUMBER(R.string.rc_register_label_phone),
    PASSWORD(R.string.rc_register_label_password)
}