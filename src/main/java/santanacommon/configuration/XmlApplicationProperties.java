/*
 * Copyright 2016 Santana Creations.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package santanacommon.configuration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import santanacommon.configuration.base.AbstractApplicationProperties;

/**
 *
 * @author Santana Creations
 */
public class XmlApplicationProperties extends AbstractApplicationProperties {
	private static final String CONFIG_FILE_DESCRIPTION = "XML Configuration File";
	private static final String CONFIG_FILENAME = "config.xml";
	private static final String CONFIG_FILE_ENCODING = "UTF-8";
	
	public XmlApplicationProperties() {
		super(CONFIG_FILE_DESCRIPTION, CONFIG_FILENAME, CONFIG_FILE_ENCODING);
	}
	
	@Override
	public void load() {
		try (FileInputStream fileInputStream = new FileInputStream(getFilename())) {
			properties.loadFromXML(fileInputStream);
		} catch (IOException ex) {
			log.log(Level.SEVERE, null, ex);
		}
	}
	
	@Override
	public void commit() {
		try (FileOutputStream fileOutputStream = new FileOutputStream(CONFIG_FILENAME)) {
			properties.storeToXML(fileOutputStream, CONFIG_FILE_DESCRIPTION, CONFIG_FILE_ENCODING);
		} catch (IOException ex) {
			log.log(Level.SEVERE, null, ex);
		}
	}
}
