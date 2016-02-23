package com.steelzack.xmladder.csv;

import com.opencsv.bean.CsvBind;

/**
 * Created by joaofilipesabinoesperancinha on 23-02-16.
 */
public class AttributeDeleteBean {
    @CsvBind
    private String name;

    @CsvBind
    private String xpath;

    public String getName() {
        return name;
    }

    public String getXpath() {
        return xpath;
    }
}
