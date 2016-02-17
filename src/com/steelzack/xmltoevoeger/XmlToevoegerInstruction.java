package com.steelzack.xmltoevoeger;

import java.util.Map;

/**
 * Created by joaofilipesabinoesperancinha on 16-02-16.
 */
public class XmlToevoegerInstruction {

    private final String xPath;

    private final Map<String, String> attributesToAdd;

    private final String attributeValueToReplace;

    private final Map<String, String> attributeKeyValueToReplaceOrAdd;

    private final Map<String, Map<String, String>> siblingsToAdd;

    private final Map<String, String> siblingToAddValues;


    public XmlToevoegerInstruction( //
                                    String xPath, //
                                    Map<String, String> attributesToAdd, //
                                    String attributeValueToReplace, //
                                    Map<String, String> attributeKeyValueToReplaceOrAdd, //
                                    Map<String, Map<String, String>> siblingsToAdd, //
                                    Map<String, String> siblingToAddValues //
    ) {
        this.xPath = xPath;
        this.attributesToAdd = attributesToAdd;
        this.attributeValueToReplace = attributeValueToReplace;
        this.attributeKeyValueToReplaceOrAdd = attributeKeyValueToReplaceOrAdd;
        this.siblingsToAdd = siblingsToAdd;
        this.siblingToAddValues = siblingToAddValues;
    }
}
