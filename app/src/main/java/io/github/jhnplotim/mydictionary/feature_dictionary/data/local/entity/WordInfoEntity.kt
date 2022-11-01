package io.github.jhnplotim.mydictionary.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.jhnplotim.mydictionary.feature_dictionary.domain.model.Meaning
import io.github.jhnplotim.mydictionary.feature_dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val meanings: List<Meaning>,
    val phonetic: String?,
    val sourceUrls: List<String>,
    val word: String,
    @PrimaryKey val id: Int? = null
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meanings = meanings,
            word = word,
            sourceUrls = sourceUrls,
            phonetic = phonetic
        )
    }
}
