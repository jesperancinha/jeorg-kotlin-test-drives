package org.jesperancinha.ktd.exceptions

import kotlinx.coroutines.runBlocking

class HandlingExceptions {
    private val gardenersIds: MutableList<Long> = mutableListOf()
    private val configurationSettings: ConfigurationSettings = ConfigurationSettings()

    suspend fun addGardenerToConfiguration(gardener: Gardener) = runCatching {
        configurationSettings.edit { preferences ->
            gardenersIds.add(gardener.id)
            preferences[GARDENERS_KEY] = gardenersIds
        }
    }

    suspend fun addGardenerToConfigurationManually(gardener: Gardener): Result<Unit> = try {
        configurationSettings.edit { preferences ->
            gardenersIds.add(gardener.id)
            preferences[GARDENERS_KEY] = gardenersIds
        }
        Result.success(Unit)
    } catch (t: Throwable) {
        Result.failure(t)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = runBlocking {
            val addGardenerToSignInHistoryOriginalResult =
                HandlingExceptions().addGardenerToConfigurationManually(Gardener(1L))
            val addGardenerToSignInHistoryResult = HandlingExceptions().addGardenerToConfiguration(Gardener(1L))

            println(addGardenerToSignInHistoryOriginalResult)
            println(addGardenerToSignInHistoryResult)
        }
    }
}

const val GARDENERS_KEY = "gardeners"

class ConfigurationSettings {
    suspend fun edit(f: suspend (MutableMap<String, MutableList<Long>>) -> Unit) {
        f(mutableMapOf())
    }
}

data class Gardener(val id: Long)
