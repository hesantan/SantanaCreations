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
package common.configuration.base;

import java.util.Properties;

public abstract class AbstractApplicationProperties {

	protected final Properties properties;
	private final String configFilename;
	private final String configFileDescription;
	private final String configFileEncoding;
	
	protected AbstractApplicationProperties(String configFileDescriptionString, String configFilenameString, String configFileEncodingString) {
		properties = new Properties();

		configFileDescription = configFileDescriptionString;
		configFilename = configFilenameString;
		configFileEncoding = configFileEncodingString;
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
