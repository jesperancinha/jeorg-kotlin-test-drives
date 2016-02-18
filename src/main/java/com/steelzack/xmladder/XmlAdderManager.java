package com.steelzack.xmladder;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.steelzack.xmladder.csv.AttributeBean;
import com.steelzack.xmladder.instruction.XmlAdderAddAttributeManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 16-02-16.
 */
public class XmlAdderManager {

    private XmlAdderAddAttributeManager addAttributeManager = new XmlAdderAddAttributeManager();
    private InputStream fileSourceDirectory;
    private InputStream fileDestinationDirectory;

    public XmlAdderManager( //
                            InputStream fileSourceDirectory,
                            InputStream fileDestinationDirectory, //
                            InputStream fileAddAttributes //
    ) throws IOException {
        this.fileSourceDirectory = fileSourceDirectory;
        this.fileDestinationDirectory = fileDestinationDirectory;

        readAllAddAttributes(fileAddAttributes);
    }

    protected void readAllAddAttributes(InputStream fileAddAttributes) throws IOException {
        final HeaderColumnNameMappingStrategy<AttributeBean> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(AttributeBean.class);
        final CsvToBean<AttributeBean> csvToBean = new CsvToBean<>();
        final List<AttributeBean> beanList = csvToBean.parse(strategy, new InputStreamReader(fileAddAttributes));

        for (AttributeBean attBean : beanList) {
            addAttributeManager.addAttribute(attBean.getName(), attBean.getValue(), attBean.getXpath());
        }
    }

    public XmlAdderAddAttributeManager getAddAttributeManager() {
        return addAttributeManager;
    }
}
