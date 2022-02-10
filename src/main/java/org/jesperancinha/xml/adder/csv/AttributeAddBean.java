package org.jesperancinha.xml.adder.csv;

import com.opencsv.bean.CsvBind;

/**
 * Created by joaofilipesabinoesperancinha on 18-02-16.
 */
public class AttributeAddBean {

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
