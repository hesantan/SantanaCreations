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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 *
 * @author Santana Creations
 */
public class Strings {
    
    public static boolean isNullOrEmpty(String str) {
	    return str == null || str.isEmpty();
    }
	
	static String urlEncode(String str) throws UnsupportedEncodingException {
		return URLEncoder.encode(str, "UTF-8");
	}
	
	static String urlDecode(String str) throws UnsupportedEncodingException {
		return URLDecoder.decode(str, "UTF-8");
	}
}
