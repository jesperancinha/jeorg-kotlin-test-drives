package com.steelzack.xmladder.csv;

import com.opencsv.bean.CsvBind;

/**
 * Created by joaofilipesabinoesperancinha on 18-02-16.
 */
public class AttributeBean {

    @CsvBind
    private String name;

    @CsvBind
    private String value;

    @CsvBind
    private String xpath;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getXpath() {
        return xpath;
    }
}
