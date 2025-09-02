package br.com.docbr.validation

import br.com.docbr.exceptions.InvalidCNPJCheckDigitException
import br.com.docbr.exceptions.InvalidCNPJFormatException
import br.com.docbr.pojo.CNPJ
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CnpjInspectorTest {

    @Test
    fun `validation should throw exception due to invalid format`() {
        val invalidCheckDigit = CNPJ("I8.55J.32K/AB09-A5").document
        val invalidBaseSize = CNPJ("I8.55J.32K/AB0-65").document
        val invalidCheckDigitSize = CNPJ("I8.55J.32K/AB09-5").document

        assertThrows<InvalidCNPJFormatException> { CNPJValidator.isEntryValid(invalidCheckDigit) }
        assertThrows<InvalidCNPJFormatException> { CNPJValidator.isEntryValid(invalidBaseSize) }
        assertThrows<InvalidCNPJFormatException> { CNPJValidator.isEntryValid(invalidCheckDigitSize) }
    }

    @Test
    fun `document format should be valid`() {
        val validDocumentFormat = CNPJ("I8.55J.32K/AB09-65").document
        assertTrue { CNPJValidator.isEntryValid(validDocumentFormat) }
    }

    @Test
    fun `verifier digit should be valid`() {
        val validDocumentVD = CNPJ("12.ABC.345/01DE-35").document
        assertTrue { CNPJValidator.isCheckDigitValid(validDocumentVD) }
    }

    @Test
    fun `verifier digit should be invalid`() {
        val invalidDocumentVD = CNPJ("12.ABC.345/01DE-58").document
        assertThrows<InvalidCNPJCheckDigitException> { CNPJValidator.isCheckDigitValid(invalidDocumentVD) }
    }

    @Test
    fun `CNPJ should be valid`() {
        val validDocumentVD = "12.ABC.345/01DE-35"
        assertTrue { CNPJValidator.isCNPJValid(validDocumentVD) }
    }

    @Test
    fun `CNPJ should be invalid`() {
        val invalidCheckDigit = "I8.55J.32K/AB09-A5"
        val invalidBaseSize = "I8.55J.32K/AB0-65"
        val invalidCheckDigitSize = "I8.55J.32K/AB09-5"
        val invalidDocumentVD = "12.ABC.345/01DE-58"

        assertFalse { CNPJValidator.isCNPJValid(invalidCheckDigit) }
        assertFalse { CNPJValidator.isCNPJValid(invalidBaseSize) }
        assertFalse { CNPJValidator.isCNPJValid(invalidCheckDigitSize) }
        assertFalse { CNPJValidator.isCNPJValid(invalidDocumentVD) }
    }

    @Test
    fun `CNPJ should be invalid, and validation should throw exceptions`() {
        val invalidCheckDigit = "I8.55J.32K/AB09-A5"
        val invalidBaseSize = "I8.55J.32K/AB0-65"
        val invalidCheckDigitSize = "I8.55J.32K/AB09-5"
        val invalidDocumentVD = "12.ABC.345/01DE-58"

        assertThrows<InvalidCNPJFormatException> { CNPJValidator.validate(invalidCheckDigit) }
        assertThrows<InvalidCNPJFormatException> { CNPJValidator.validate(invalidBaseSize) }
        assertThrows<InvalidCNPJFormatException> { CNPJValidator.validate(invalidCheckDigitSize) }
        assertThrows<InvalidCNPJCheckDigitException> { CNPJValidator.validate(invalidDocumentVD) }
    }

}