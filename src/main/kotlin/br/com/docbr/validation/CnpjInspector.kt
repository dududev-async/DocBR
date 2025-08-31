package br.com.docbr.validation

import br.com.docbr.pojo.CNPJ

object CnpjInspector {

    val documentPattern: Regex = Regex("([0-9A-Z]{12})([0-9]{2})")

    fun isCNPJValid(value: CNPJ): Boolean {
        return isEntryValid(value.document) && isCheckDigitValid(value.document)
    }

    fun isCNPJValid(value: String): Boolean {
        val document = CNPJ(value).document
        return isEntryValid(document) && isCheckDigitValid(document)
    }

    /**
     * This method performs validation using a regular expression, ensuring that the
     * CNPJ follows the expected format:
     *
     *   SS.SSS.SSS/SSSS-NN
     *
     *   S - Letter or Number
     *
     *   N - Number
     */
    fun isEntryValid(document: String): Boolean {
        val patternMatches = documentPattern.matches(document)
        if (!patternMatches) { throw Exception("Invalid CNPJ") }
        return true
    }

    /**
     * Validates the check digits of the given CNPJ, ensuring that the document
     * number conforms to the official calculation rules.
     */
    fun isCheckDigitValid(document: String): Boolean {
        val dv = document.substring(document.length - 2 .. document.length - 1)
        val baseDoc = document.substring(0 .. document.length - 3)
        if (dv != calcCheckDigit(baseDoc)) throw Exception("Invalid Verifier Digit")
        return true
    }

    /**
     * Calculates the CNPJ alphanumeric check digits (DV)
     * according to the official calculation rules.
     */
    private fun calcCheckDigit(document: String): String {
        val firstDigit = calcSingleDigit(document)
        val secondDigit = calcSingleDigit(document.plus(firstDigit))
        return "$firstDigit$secondDigit"
    }

    /**
     * Calculation rules for the CNPJ Alphanumeric check digits (DV):
     *
     * 1. Each character (number or letter) is converted into its decimal ASCII value, minus 48
     *
     * 2. Apply weight factors from 2 to 9, moving right-to-left, repeating the cycle as needed.
     *    Example sequence (for 12 characters): 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5.
     *
     * 3. Multiply each character value by its corresponding weight and sum the results.
     *
     * 4. Calculate the remainder (sum % 11):
     *    - If the remainder is 0 or 1, the check digit (DV) = 0.
     *    - Otherwise, DV = 11 - remainder.
     */
    private fun calcSingleDigit(document: String): Int {
        val result = document.reversed().foldIndexed(0 to 2) { _, (acc, weight), char ->
            val digit = char.code - 48
            val newAcc = acc + (digit * weight)
            val nextWeight = if (weight == 9) 2 else weight + 1
            newAcc to nextWeight
        }.first

        return when (result % 11) {
            0, 1 -> 0
            else -> 11 - (result % 11)
        }
    }

}
