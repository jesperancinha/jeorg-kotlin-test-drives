package org.jesperancinha.asnsei.modularity

import kotlin.PublishedApi

class CacheSystem {
    @PublishedApi
    internal var cacheData: Map<String, String> = emptyMap()
    
    fun addToCache(key: String, value: String) {
        cacheData = cacheData + (key to value)
    }

    fun getFromCache(key: String): String? = cacheData[key]

    /**
     * Not using inline will mean that cacheData is always accessible
     * Using it will mean also inlining cacheData but that is normally not possible for internal members
     * Using @PublishedApi, we are able to inline the rest of the code.
     */
    fun debugCache() {
        println("Cache State: $cacheData")
    }
}