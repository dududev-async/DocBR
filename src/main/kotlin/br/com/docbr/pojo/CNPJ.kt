package br.com.docbr.pojo

import br.com.docbr.validation.CnpjInspector

class CNPJ(value: String) {
    val document: String = value
        .filter { it.isLetterOrDigit() }
        .uppercase()
}

fun CNPJ.isValid(): Boolean =
    CnpjInspector.isCNPJValid(this)

