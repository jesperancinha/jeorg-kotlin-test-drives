package org.jesperancinha.xml.adder.instruction

/**
 * Created by joaofilipesabinoesperancinha on 16-02-16.
 */
class XmlAdderInstruction
/**
 * Only attributesToAdd attribute will be available on version Zero
 *
 * @param attributeValueToReplace
 * @param attributesToAdd
 * @param attributeKeyValueToReplaceOrAdd
 * @param siblingsToAdd
 * @param siblingToAddValues
 */(
    private val attributeValueToReplace: String? = null,
    internal val attributesToAdd: MutableMap<String, String>,
    private val attributeKeyValueToReplaceOrAdd: Map<String, String>? = null,
    internal val attributeKeysToDelete: MutableSet<String>,
    private val siblingsToAdd: Map<String, Map<String, String>>? = null,
    private val siblingToAddValues: Map<String, String>? = null
) {
    fun addAddAttribute(name: String, value: String?) = value?.let { attributesToAdd[name] = value }

    fun addDeleteAttribute(name: String?) = name?.let {
        attributeKeysToDelete.add(name)
    }

    fun getAttributesToAdd(): Map<String, String> {
        return attributesToAdd
    }

    fun getAttributeKeysToDelete(): Set<String> {
        return attributeKeysToDelete
    }
}