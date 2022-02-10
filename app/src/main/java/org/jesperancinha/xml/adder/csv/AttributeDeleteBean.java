package org.jesperancinha.xml.adder.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

/**
 * Created by joaofilipesabinoesperancinha on 23-02-16.
 */
public class AttributeDeleteBean {
    @CsvBindByName(column = "name")
    @CsvBindByPosition(position = 0)
    private String name;

    @CsvBindByName(column = "xpath")
    @CsvBindByPosition(position = 1)
    private String xpath;

    public String getName() {
        return name;
    }

    public String getXpath() {
        return xpath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }
}
