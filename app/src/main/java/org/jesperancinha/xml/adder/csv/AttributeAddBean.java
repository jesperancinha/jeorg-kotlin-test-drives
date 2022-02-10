package org.jesperancinha.xml.adder.csv;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByNames;

/**
 * Created by joaofilipesabinoesperancinha on 18-02-16.
 */
public class AttributeAddBean {

    @CsvBindByName(capture = "name")
    private String name;

    @CsvBindByName(capture = "value")
    private String value;

    @CsvBindByName(capture = "xpath")
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
