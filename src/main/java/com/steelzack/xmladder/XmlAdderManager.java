package com.steelzack.xmladder;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.steelzack.xmladder.csv.AttributeBean;
import com.steelzack.xmladder.instruction.XmlAdderAddAttributeManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 16-02-16.
 */
public class XmlAdderManager {

    private XmlAdderAddAttributeManager addAttributeManager = new XmlAdderAddAttributeManager();
    private File fileSourceDirectory;
    private File fileDestinationDirectory;

    public XmlAdderManager( //
                            File fileSourceDirectory,
                            File fileDestinationDirectory, //
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

    protected static List<File> listAllFilesToChange(final File sourceDrectory) {
        final List<File> allXmlsToChahge = new ArrayList<>();
        final File[] fileList = sourceDrectory.listFiles();
        for (final File f : fileList) {
            if(f.isDirectory())
            {
                allXmlsToChahge.addAll(listAllFilesToChange(f));
            } else if (f.isFile())
            {
                if(f.getName().endsWith("xml")){
                    allXmlsToChahge.add(f);
                }
            }
        }
        return allXmlsToChahge;
    }


}
