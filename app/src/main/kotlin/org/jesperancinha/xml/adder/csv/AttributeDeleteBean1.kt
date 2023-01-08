package org.jesperancinha.xml.adder.csv

import com.opencsv.bean.CsvBindByName
import com.opencsv.bean.CsvBindByPosition

/**
 * Created by joaofilipesabinoesperancinha on 23-02-16.
 */
class AttributeDeleteBean {
    @CsvBindByName(column = "name")
    @CsvBindByPosition(position = 0)
    var name: String? = null

    @CsvBindByName(column = "xpath")
    @CsvBindByPosition(position = 1)
    var xpath: String? = null
}