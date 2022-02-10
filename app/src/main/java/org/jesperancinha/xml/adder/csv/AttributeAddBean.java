package org.jesperancinha.xml.adder.csv;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

/**
 * Created by joaofilipesabinoesperancinha on 18-02-16.
 */
public class AttributeAddBean {

    @CsvBindByName(capture = "name")
    @CsvBindByPosition(position = 0)
    private String name;

    @CsvBindByName(capture = "value")
    @CsvBindByPosition(position = 1)
    private String value;

    @CsvBindByName(capture = "xpath")
    @CsvBindByPosition(position = 2)
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

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }
}
