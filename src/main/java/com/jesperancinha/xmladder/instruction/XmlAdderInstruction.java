package com.jesperancinha.xmladder.instruction;

import java.util.Map;
import java.util.Set;

/**
 * Created by joaofilipesabinoesperancinha on 16-02-16.
 */
public class XmlAdderInstruction {

    private final Map<String, String> attributesToAdd;

    private final String attributeValueToReplace;

    private final Map<String, String> attributeKeyValueToReplaceOrAdd;

    private final Set<String> attributeKeysToDelete;

    private final Map<String, Map<String, String>> siblingsToAdd;

    private final Map<String, String> siblingToAddValues;


    /**
     * Only attributesToAdd attribute will be available on version Zero
     *
     * @param attributeValueToReplace
     * @param attributesToAdd
     * @param attributeKeyValueToReplaceOrAdd
     * @param siblingsToAdd
     * @param siblingToAddValues
     */
    public XmlAdderInstruction( //
                                String attributeValueToReplace, //
                                Map<String, String> attributesToAdd, //
                                Map<String, String> attributeKeyValueToReplaceOrAdd, //
                                Set<String> attributeKeyToDelete, //
                                Map<String, Map<String, String>> siblingsToAdd, //
                                Map<String, String> siblingToAddValues //
    ) {
        this.attributesToAdd = attributesToAdd;
        this.attributeValueToReplace = attributeValueToReplace;
        this.attributeKeyValueToReplaceOrAdd = attributeKeyValueToReplaceOrAdd;
        this.siblingsToAdd = siblingsToAdd;
        this.siblingToAddValues = siblingToAddValues;
        this.attributeKeysToDelete = attributeKeyToDelete;
    }

    public void addAddAttribute(String name, String value) {
        attributesToAdd.put(name, value);
    }

    public void addDeleteAttribute(String name) {
        attributeKeysToDelete.add(name);
    }

    public Map<String, String> getAttributesToAdd() {
        return attributesToAdd;
    }

    public Set<String> getAttributeKeysToDelete() {
        return attributeKeysToDelete;
    }
}
