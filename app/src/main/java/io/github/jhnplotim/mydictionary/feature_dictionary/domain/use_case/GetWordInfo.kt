package io.github.jhnplotim.mydictionary.feature_dictionary.domain.use_case

import io.github.jhnplotim.mydictionary.core.util.Resource
import io.github.jhnplotim.mydictionary.feature_dictionary.domain.model.WordInfo
import io.github.jhnplotim.mydictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWordInfo @Inject constructor(
    private val repository: WordInfoRepository
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            return flow {  }
        }

        return repository.getWordInfo(word)
    }
}