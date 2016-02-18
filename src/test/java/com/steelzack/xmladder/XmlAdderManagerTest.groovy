package com.steelzack.xmladder

import com.steelzack.xmladder.instruction.XmlAdderAddAttributeManager
import com.steelzack.xmladder.instruction.XmlAdderInstruction
import org.junit.Assert
import org.junit.Test

/**
 * Created by joaofilipesabinoesperancinha on 18-02-16.
 */
class XmlAdderManagerTest {

    @Test
    void testReadAllAddAttributes() {
        final InputStream is = getClass().getResourceAsStream("testReadAttributeBean.csv");
        final XmlAdderManager manager = new XmlAdderManager(null, null, is)

        final XmlAdderAddAttributeManager attributeManager = manager.getAddAttributeManager();
        final Map<String, XmlAdderInstruction> result = attributeManager.getXmlAdderInstructionArrayMap();
        final resultSet = result.keySet()

        Assert.assertEquals(1,  resultSet.size());
        for (String key : resultSet) {
            Assert.assertEquals("testnode1/testnode2", key);
            Assert.assertEquals("attribute1value", result.get(key).getAttributesToAdd().get("attribute1name"));
        }
    }
}
