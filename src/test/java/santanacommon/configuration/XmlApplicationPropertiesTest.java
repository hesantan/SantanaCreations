/*
 * Copyright 2016 Santana Creations.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package santanacommon.configuration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * XML Configuration file tester
 *
 * @author Hector
 * @since 10/16/2016
 */
public class XmlApplicationPropertiesTest {
	
	private final XmlApplicationProperties xmlApplicationProperties;
	
	public XmlApplicationPropertiesTest() {
		xmlApplicationProperties = new XmlApplicationProperties();
	}
	
	@Before
	public void setUp() {
		xmlApplicationProperties.commit();
	}
	
	@After
	public void tearDown() {
		deleteConfigFile(xmlApplicationProperties.getFilename());
	}

	@Test
	public void membersTest() {
		Assert.assertEquals("XML Configuration File", xmlApplicationProperties.getFileDescription());
		Assert.assertEquals("config.xml", xmlApplicationProperties.getFilename());
		Assert.assertEquals("UTF-8", xmlApplicationProperties.getFileEncoding());
	}

	@Test
	public void load() {
		xmlApplicationProperties.load();
	}

	@Test
	public void testFileNotFoundException() {
		XmlApplicationProperties xmlApplicationPropertiesTemp = new XmlApplicationProperties("");
		xmlApplicationPropertiesTemp.commit();
		xmlApplicationPropertiesTemp.load();
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	private void deleteConfigFile(String configFilePath) {
		File configFile = new File(configFilePath);
		Assert.assertNotNull(configFile.delete());
	}
}