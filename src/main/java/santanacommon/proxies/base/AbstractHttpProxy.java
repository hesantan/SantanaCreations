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
package santanacommon.proxies.base;

import santanacommon.utilities.LoggerFactory;
import santanacommon.utilities.Strings;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santana Creations
 */
public abstract class AbstractHttpProxy {
	private static final String DELETE_METHOD_NAME = "DELETE";
	private final String acceptCharset;
	private final String defaultContentType;
	private final Logger log;
	
	protected AbstractHttpProxy(String defaultContentType, String acceptCharset) {
		this.acceptCharset = acceptCharset;
		this.defaultContentType = defaultContentType;
		log = LoggerFactory.getLogger(getClass().getName());
	}
	
	public String doGet(String uri) {
		return sendHttpRequest("GET", buildUrl(uri), null);
	}
    
	public String doPost(String uri, String data) {
		return sendHttpRequest("POST", buildUrl(uri), data);
	}
    
	public String doPut(String uri, String data) {
		return sendHttpRequest("PUT", buildUrl(uri), data);
	}
	
	public String doDelete(String uri, String data) {
		return sendHttpRequest(DELETE_METHOD_NAME, buildUrl(uri), data);
	}
    
	// GET
	protected abstract String getRequestContentTypeForGet();
	protected abstract Map<String, String> getRequestHeadersForGet();
	
	// POST
	protected abstract String getRequestContentTypeForPost();
	protected abstract Map<String, String> getRequestHeadersForPost();
	
	// PUT
	protected abstract String getRequestContentTypeForPut();
	protected abstract Map<String, String> getRequestHeadersForPut();
	
	// DELETE
	protected abstract String getRequestContentTypeForDelete();
	protected abstract Map<String, String> getRequestHeadersForDelete();
	
	protected abstract String buildUrl(String uri);
	
	private String getContentType(String method) {
		switch(method.toUpperCase()) {
			case "GET":
				return getRequestContentTypeForGet();
			case "POST":
				return getRequestContentTypeForPost();
			case "PUT":
				return getRequestContentTypeForPut();
			case DELETE_METHOD_NAME:
				return getRequestContentTypeForDelete();
			default:
				return defaultContentType;
		}
	}
	
	private Map<String, String> getHeaders(String method) {
		switch(method.toUpperCase()) {
			case "GET":
				return getRequestHeadersForGet();
			case "POST":
				return getRequestHeadersForPost();
			case "PUT":
				return getRequestHeadersForPut();
			case DELETE_METHOD_NAME:
				return getRequestHeadersForDelete();
			default:
				return null;
		}
	}
	
	private String sendHttpRequest(String method, String url, String data) {
		String contentType = getContentType(method);
		Map<String, String> headers = getHeaders(method);
		return sendHttpRequest(method, headers, contentType, url, data);
	}
	
	private String sendHttpRequest(String method, Map<String, String> headers, String contentType, String url,  String data) {
		
		if (Strings.isNullOrEmpty(url)) {
			return "";
		}
		
		try {
            URL urlObject = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) urlObject.openConnection();
			
            urlConnection.setDoOutput(!Strings.isNullOrEmpty(data));
            urlConnection.setRequestMethod(method);
			urlConnection.setRequestProperty("Accept-Charset", acceptCharset);
			urlConnection.setRequestProperty("Content-Type", Strings.isNullOrEmpty(contentType) ? defaultContentType : contentType);
			
			if (headers != null && !headers.isEmpty()) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					urlConnection.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			
            int responseCode = urlConnection.getResponseCode();
            
            log.log(Level.INFO, "Sending \"{0}\" request to URL : {1}", new Object[]{method, url});
            log.log(Level.INFO, "Response Code : {0}", responseCode);
			
			StringBuilder response;
			
			try (BufferedReader in = new BufferedReader(
					new InputStreamReader(urlConnection.getInputStream(), acceptCharset))) {
				String inputLine;
				response = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
                }
            } finally {
                urlConnection.disconnect();
            }
			
			return response.toString();
		} catch (Exception ex) {
			log.log(Level.SEVERE, null, ex);
        }
        return "";
	}
}