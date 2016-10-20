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

package common.utilities;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Test methods for CustomLoggerFactory
 *
 * @author Hector
 * @since 10/18/2016
 */
public class CustomLoggerFactoryTest {

	@Test
	public void testConstructorIsPrivate() {
		Logger log = CustomLoggerFactory.getLogger(getClass().getName());

		try {
			Constructor<CustomLoggerFactory> constructor = CustomLoggerFactory.class.getDeclaredConstructor();
			Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
			constructor.setAccessible(true);
			constructor.newInstance();
		} catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ex) {
			log.log(Level.FINE, null, ex);
		}
	}

	@Test
	public void getLogger() {
		Logger log = CustomLoggerFactory.getLogger(getClass().getName());
		log.log(Level.FINE, "Logging FINE");
	}

}