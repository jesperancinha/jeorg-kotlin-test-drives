package org.jesperancinha.xml.adder.csv;

import com.opencsv.bean.CsvBindByName;

/**
 * Created by joaofilipesabinoesperancinha on 23-02-16.
 */
public class AttributeDeleteBean {
    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "xpath")
    private String xpath;

    public String getName() {
        return name;
    }

    public String getXpath() {
        return xpath;
    }
}
