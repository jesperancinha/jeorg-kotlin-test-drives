package org.jesperancinha.asnsei.modularity

/**
 * This second module is here only to observe the cacheData behaviour
 * The call to getFromCache gets inline and that is only possible for internal members when the @PublishedApi is used on them.
 */
fun main() {
    val cache = CacheSystem()
    cache.addToCache("user1", "JohnDoe")
    cache.addToCache("user2", "JaneDoe")
    println("User1 data: ${cache.getFromCache("user1")}")
    cache.debugCache()
    val cacheDataField = CacheSystem::class.java.getDeclaredField("cacheData")
    cacheDataField.isAccessible = true
    val internalCache = cacheDataField.get(cache)
    println("Internal cache accessed via reflection: $internalCache")
}
