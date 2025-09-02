package br.com.docbr.exceptions

class InvalidCNPJFormatException(
    override val message: String = "Invalid CNPJ format, ensure that the CNPJ follows the expected format: SS.SSS.SSS/SSSS-NN",
) : InvalidCNPJException(message)