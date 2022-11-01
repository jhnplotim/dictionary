package io.github.jhnplotim.mydictionary.feature_dictionary.domain.repository

import io.github.jhnplotim.mydictionary.core.util.Resource
import io.github.jhnplotim.mydictionary.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}