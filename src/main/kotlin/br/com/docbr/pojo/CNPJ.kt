package br.com.docbr.pojo

import br.com.docbr.exceptions.InvalidCNPJException
import br.com.docbr.validation.CNPJValidator

class CNPJ(value: String) {
    val document: String = value
        .filter { it.isLetterOrDigit() }
        .uppercase()
}

fun CNPJ.isValid(): Boolean =
    CNPJValidator.isCNPJValid(this)

@Throws(InvalidCNPJException::class)
fun CNPJ.validate() {
    CNPJValidator.validate(this)
}