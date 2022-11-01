/*
 * Copyright 2022 John Paul Otim
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.jhnplotim.mydictionary.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jhnplotim.mydictionary.feature_dictionary.data.local.Converters
import io.github.jhnplotim.mydictionary.feature_dictionary.data.local.WordInfoDao
import io.github.jhnplotim.mydictionary.feature_dictionary.data.local.WordInfoDatabase
import io.github.jhnplotim.mydictionary.feature_dictionary.data.remote.DictionaryApi
import io.github.jhnplotim.mydictionary.feature_dictionary.data.repository.WordInfoRepositoryImpl
import io.github.jhnplotim.mydictionary.feature_dictionary.data.util.GsonParser
import io.github.jhnplotim.mydictionary.feature_dictionary.data.util.JsonParser
import io.github.jhnplotim.mydictionary.feature_dictionary.domain.repository.WordInfoRepository
import io.github.jhnplotim.mydictionary.feature_dictionary.domain.use_case.GetWordInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {
    @Provides
    @Singleton
    fun providesGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfo {
        return GetWordInfo(repository)
    }

    @Provides
    @Singleton
    fun providesWordInfoRepository(
        db: WordInfoDatabase,
        api: DictionaryApi
    ): WordInfoRepository {
        return WordInfoRepositoryImpl(api,db.dao)
    }

    @Provides
    @Singleton
    fun providesWordInfoDatabase(app: Application, jsonParser: JsonParser): WordInfoDatabase {
        return Room.databaseBuilder(app, WordInfoDatabase::class.java, "word_db")
            .addTypeConverter(Converters(jsonParser))
            .build()
    }


    @Provides
    @Singleton
    fun providesJsonParser(): JsonParser {
        return GsonParser(Gson())
    }


    @Provides
    @Singleton
    fun providesDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}