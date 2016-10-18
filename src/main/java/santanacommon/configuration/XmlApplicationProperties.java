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

import santanacommon.configuration.base.AbstractApplicationProperties;
import santanacommon.utilities.CustomLoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santana Creations
 */
class XmlApplicationProperties extends AbstractApplicationProperties {

	private final Logger log;

	XmlApplicationProperties() {
		super("XML Configuration File", "config.xml", "UTF-8");
		log = CustomLoggerFactory.getLogger(getClass().getName());
	}

	XmlApplicationProperties(String configFile) {
		super("XML Configuration File", configFile, "UTF-8");
		log = CustomLoggerFactory.getLogger(getClass().getName());
	}
	
	@Override
	public void load() {
		try (FileInputStream fileStream = new FileInputStream(getFilename())) {
			properties.loadFromXML(fileStream);
		} catch (IOException ex) {
			log.log(Level.FINE, "XML Configuration could not be loaded.", ex);
		}
	}
	
	@Override
	public void commit() {
		try (FileOutputStream fileStream = new FileOutputStream(getFilename())) {
			properties.storeToXML(fileStream, getFileDescription(), getFileEncoding());
		} catch (IOException ex) {
			log.log(Level.FINE, "XML Configuration could not be saved.", ex);
		}
	}
}
