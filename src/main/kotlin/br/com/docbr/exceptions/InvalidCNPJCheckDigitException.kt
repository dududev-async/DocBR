package br.com.docbr.exceptions

class InvalidCNPJCheckDigitException (
    override val message: String = "The CNPJ check digit does not conforms to the official calculation rules."
) : InvalidCNPJException(message)