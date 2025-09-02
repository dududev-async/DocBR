package br.com.docbr.exceptions

open class InvalidCNPJException(
    override val message: String?
) : Exception(message)