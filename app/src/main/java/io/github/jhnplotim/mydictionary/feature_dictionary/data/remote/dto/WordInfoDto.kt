package io.github.jhnplotim.mydictionary.feature_dictionary.data.remote.dto

import io.github.jhnplotim.mydictionary.feature_dictionary.data.local.entity.WordInfoEntity
import io.github.jhnplotim.mydictionary.feature_dictionary.domain.model.WordInfo

data class WordInfoDto(
    val license: LicenseDto,
    val meanings: List<MeaningDto>,
    val phonetic: String?,
    val phonetics: List<PhoneticDto> = emptyList(),
    val sourceUrls: List<String> = emptyList(),
    val word: String
) {

    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            sourceUrls = sourceUrls,
            word = word,
            phonetic = phonetic,
            meanings = meanings.map { it.toMeaning() }
        )
    }
}