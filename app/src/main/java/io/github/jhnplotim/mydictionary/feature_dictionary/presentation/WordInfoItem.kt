package io.github.jhnplotim.mydictionary.feature_dictionary.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.jhnplotim.mydictionary.feature_dictionary.domain.model.WordInfo

@Composable
fun WordInfoItem(
    wordInfo: WordInfo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = wordInfo.word,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        wordInfo.phonetic?.let {
            Text(text = wordInfo.phonetic, fontWeight = FontWeight.Light)
        }
        Spacer(modifier = Modifier.height(16.dp))
        wordInfo.sourceUrls.forEach { sourceUrl ->
            Text(text = sourceUrl)
        }

        wordInfo.meanings.forEach { meaning ->
            Text(text = meaning.partOfSpeech, fontWeight = FontWeight.Bold)
            meaning.definitions.forEachIndexed { i, definition ->
                Text(text = "${i + 1}. ${definition.definition}")
                Spacer(modifier = Modifier.height(8.dp))
                definition.example?.let { example ->
                    Text(text = "Example: $example")
                    Spacer(modifier = Modifier.height(8.dp))
                }
                if (definition.antonyms.isNotEmpty()) {
                    Text(text = "Antonyms: ${definition.antonyms.joinToString(", ")}")
                    Spacer(modifier = Modifier.height(8.dp))
                }
                if (definition.synonyms.isNotEmpty()) {
                    Text(text = "Synonyms: ${definition.synonyms.joinToString(", ")}")
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
