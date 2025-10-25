package br.com.innovarix.runningclub.core.extensions

fun String.unMask() = this.replace("[^0-9]".toRegex(), "")
