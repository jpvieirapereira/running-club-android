package br.com.innovarix.runningclub.register.navigation

import br.com.innovarix.runningclub.register.domain.RCRegisterModel

sealed interface RCRegisterNavigation {
    data object Lead: RCRegisterNavigation
    data class Register(val register: RCRegisterModel): RCRegisterNavigation
    data object Plans: RCRegisterNavigation
}