/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.schematron.processor;

import javax.xml.transform.Templates;

import org.apache.camel.component.schematron.util.Utils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

/**
 * SchematronEngine Unit Test.
 */
public class SchematronProcessorTest {

    private Logger logger = LoggerFactory.getLogger(SchematronProcessorTest.class);

    @Test
    public void testValidXML() throws Exception {

        String payload = IOUtils.toString(ClassLoader.getSystemResourceAsStream("xml/article-1.xml"));
        logger.info("Validating payload: {}", payload);
       
        // validate
        String result = getProcessor("sch/schematron-1.sch").validate(payload);
        logger.info("Schematron Report: {}", result);
        assertEquals(0, Integer.valueOf(Utils.evaluate("count(//svrl:failed-assert)", result)).intValue());
        assertEquals(0, Integer.valueOf(Utils.evaluate("count(//svrl:successful-report)", result)).intValue());

    }

    @Test
    public void testInValidXML() throws Exception {

        String payload = IOUtils.toString(ClassLoader.getSystemResourceAsStream("xml/article-2.xml"));
        logger.info("Validating payload: {}", payload);
        // validate
        String result = getProcessor("sch/schematron-2.sch").validate(payload);
        logger.info("Schematron Report: {}", result);
        // should throw two assertions because of the missing chapters in the XML.
        assertEquals("A chapter should have a title", Utils.evaluate("//svrl:failed-assert/svrl:text", result));
        assertEquals("'chapter' element has more than one title present", Utils.evaluate("//svrl:successful-report/svrl:text", result).trim());


    }

    /**
     * Returns schematron processor
     *
     * @param schematron
     * @return
     */
    private SchematronProcessor getProcessor(final String schematron) {
        Templates rules = TemplatesFactory.newInstance().newTemplates(ClassLoader.getSystemResourceAsStream(schematron));
        return SchematronProcessorFactory.newScehamtronEngine(rules);
    }
}
