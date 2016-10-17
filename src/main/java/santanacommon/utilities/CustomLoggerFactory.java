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

package santanacommon.utilities;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Logger factory for package classes
 *
 * @author Hector
 * @since 10/16/2016
 */
public class CustomLoggerFactory {

	private CustomLoggerFactory() {
		
	}
	
	public static Logger getLogger(String loggerName) {
		Logger log = Logger.getLogger(loggerName);
		log.setLevel(Level.INFO);
		return log;
	}
	
}
