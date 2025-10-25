package br.com.innovarix.runningclub.di

import br.com.innovarix.runningclub.register.RCRegisterViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val presentationModule = module {
    viewModel { RCRegisterViewModel() }
}


private val lazyModules by lazy { loadKoinModules(presentationModule) }

fun loadModules() = lazyModules