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
 *
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
 * Test class for String utilities
 *
 * @author Hector
 * @since 10/16/2016
 */
public class StringsTest {

	@Test
	public void testConstructorIsPrivate() {
		Logger log = CustomLoggerFactory.getLogger(getClass().getName());

		try {
			Constructor<Strings> constructor = Strings.class.getDeclaredConstructor();
			Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
			constructor.setAccessible(true);
			constructor.newInstance();
		} catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ex) {
			log.log(Level.FINE, null, ex);
		}
	}

	@Test
	public void isNullOrEmpty() {
		Assert.assertTrue(Strings.isNullOrEmpty(""));
		Assert.assertTrue(Strings.isNullOrEmpty(null));
		Assert.assertFalse(Strings.isNullOrEmpty("Test String"));
	}

	@Test
	public void urlEncoding() {
		String decodedString = " The Test String +!#$%&'()*+,-./ ";
		String encodedString = Strings.urlEncode(decodedString);
		Assert.assertEquals(Strings.urlDecode(encodedString), decodedString);
	}

	@Test(expected = AssertionError.class)
	public void decodeUnsupportedEncodingExceptionTest() {
		Assert.assertEquals(Strings.urlDecode("", ""), Strings.urlDecode("", ""));
	}

	@Test(expected = AssertionError.class)
	public void encodeUnsupportedEncodingExceptionTest() {
		Assert.assertEquals(Strings.urlEncode("", ""), Strings.urlEncode("", ""));
	}
}