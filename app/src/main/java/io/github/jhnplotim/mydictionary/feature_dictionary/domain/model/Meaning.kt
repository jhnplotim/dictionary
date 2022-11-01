package io.github.jhnplotim.mydictionary.feature_dictionary.domain.model


data class Meaning(
    val antonyms: List<String>,
    val definitions: List<Definition>,
    val partOfSpeech: String,
    val synonyms: List<String>
)
