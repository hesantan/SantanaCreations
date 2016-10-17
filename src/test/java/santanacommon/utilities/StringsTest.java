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

package santanacommon.utilities;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
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
	public void isNullOrEmpty() {
		Assert.assertTrue(Strings.isNullOrEmpty(""));
		Assert.assertTrue(Strings.isNullOrEmpty(null));
		Assert.assertFalse(Strings.isNullOrEmpty("Test String"));
	}
	
	@Test
	public void urlEncoding() {
		try {
			String decodedString = " The Test String +!#$%&'()*+,-./ ";
			String encodedString = Strings.urlEncode(decodedString);
			Assert.assertEquals(Strings.urlDecode(encodedString), decodedString);
		} catch (UnsupportedEncodingException ex) {
			Logger log = CustomLoggerFactory.getLogger(StringsTest.class.getName());
			log.log(Level.SEVERE, null, ex);
		}
	}
}