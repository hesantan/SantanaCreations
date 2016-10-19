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
package realestate.proxies.zillow;

import santanacommon.proxies.base.AbstractHttpProxy;
import santanacommon.utilities.Strings;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Santana Creations
 */
public class ZillowHttpProxy extends AbstractHttpProxy {

	private static final String DEFAULT_CONTENT_TYPE = "text/xml";
	private final String baseUrl;
	private final String apiKey;

	public ZillowHttpProxy(String baseUrlString, String apiKeyString) {
		super(DEFAULT_CONTENT_TYPE, "UTF-8");
		baseUrl = baseUrlString;
		apiKey = apiKeyString;
	}
	
	@Override
	protected String buildUrl(String uri) {

		if (Strings.isNullOrEmpty(uri)) {
			return "";
		}
		
		String url = baseUrl + "/webservice/" + uri;
		url += (uri.contains("?") ? "&" : "?" );
		String apiKeyIdentifier = "zws-id";
		url += apiKeyIdentifier + "=" + apiKey;
		return url;
	}
	
	// GET
	@Override
	protected String getRequestContentTypeForGet() {
		return DEFAULT_CONTENT_TYPE;
	}

	@Override
	protected Map<String, String> getRequestHeadersForGet() {
		Map<String, String> headers = new HashMap<>();
		headers.put("Date", new Date().toString());
		return headers;
	}

	// POST
	@Override
	protected String getRequestContentTypeForPost() {
		return null;
	}

	@Override
	protected Map<String, String> getRequestHeadersForPost() {
		return new HashMap<>();
	}

	// PUT
	@Override
	protected String getRequestContentTypeForPut() {
		return null;
	}

	@Override
	protected Map<String, String> getRequestHeadersForPut() {
		return null;
	}

	// DELETE
	@Override
	protected String getRequestContentTypeForDelete() {
		return null;
	}

	@Override
	protected Map<String, String> getRequestHeadersForDelete() {
		return null;
	}
}
