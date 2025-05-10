package org.jesperancinha.xml.adder.csv

import com.opencsv.bean.CsvBindByName
import com.opencsv.bean.CsvBindByPosition

/**
 * Created by joaofilipesabinoesperancinha on 18-02-16.
 */
class AttributeAddBean {
    @CsvBindByName(capture = "name")
    @CsvBindByPosition(position = 0)
    var name: String? = null

    @CsvBindByName(capture = "value")
    @CsvBindByPosition(position = 1)
    var value: String? = null

    @CsvBindByName(capture = "xpath")
    @CsvBindByPosition(position = 2)
    var xpath: String? = null
}