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
package santanacommon.configuration.base;

import santanacommon.utilities.Strings;

import java.util.Properties;

/**
 *
 * @author Santana Creations
 */
public abstract class AbstractApplicationProperties {
	private static String configFilename = "config.xml";
	private static String configFileDescription = "Configuration File";
	private static String configFileEncoding = "UTF-8";
	protected final Properties properties;
	
	protected AbstractApplicationProperties(String configFileDescriptionString, String configFilenameString, String configFileEncodingString) {
		properties = new Properties();

		if (!Strings.isNullOrEmpty(configFileDescriptionString)) {
			configFileDescription = configFileDescriptionString;
		}

		if (!Strings.isNullOrEmpty(configFilenameString)) {
			configFilename = configFilenameString;
		}

		if (!Strings.isNullOrEmpty(configFileEncodingString)) {
			configFileEncoding = configFileEncodingString;
		}
	}
	
	public String getFileDescription() {
		return configFileDescription;
	}
	
	public String getFilename() {
		return configFilename;
	}
	
	public String getFileEncoding() {
		return configFileEncoding;
	}
	
	public abstract void load();
	
	public abstract void commit();
}
