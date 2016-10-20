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
package common.utilities;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santana Creations
 */
public class Strings {

	private static final String DEFAULT_URL_ENCODING = "UTF-8";

	private Strings() {
	}

    public static boolean isNullOrEmpty(String str) {
	    return str == null || str.isEmpty();
    }

	public static String urlEncode(String str) {
		return urlEncode(str, DEFAULT_URL_ENCODING);
	}

	public static String urlDecode(String str) {
		return urlDecode(str, DEFAULT_URL_ENCODING);
	}

	public static String urlEncode(String str, String encoding) {
		try {
			return URLEncoder.encode(str, encoding);
		} catch (UnsupportedEncodingException e) {

			Logger log = CustomLoggerFactory.getLogger(Strings.class.getName());
			log.log(Level.FINE, "URL could not be " + encoding + " encoded", e);

			throw new AssertionError(DEFAULT_URL_ENCODING + " not supported");
		}
	}

	public static String urlDecode(String str, String encoding) {
		try {
			return URLDecoder.decode(str, encoding);
		} catch (UnsupportedEncodingException e) {

			Logger log = CustomLoggerFactory.getLogger(Strings.class.getName());
			log.log(Level.FINE, "URL could not be " + encoding + " decoded", e);

			throw new AssertionError(DEFAULT_URL_ENCODING + " not supported");
		}
	}
}
