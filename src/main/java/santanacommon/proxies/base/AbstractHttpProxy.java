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

import santanacommon.utilities.CustomLoggerFactory;
import santanacommon.utilities.Strings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
	private static final String GET_METHOD_NAME = "GET";
	private static final String POST_METHOD_NAME = "POST";
	private static final String PUT_METHOD_NAME = "PUT";
	private static final String DELETE_METHOD_NAME = "DELETE";

	private final String acceptCharset;
	private final String defaultContentType;
	private final Logger log;
	
	protected AbstractHttpProxy(String defaultContentType, String acceptCharset) {
		this.acceptCharset = acceptCharset;
		this.defaultContentType = defaultContentType;
		log = CustomLoggerFactory.getLogger(getClass().getName());
	}
	
	public String doGet(String uri) {
		return sendHttpRequest(GET_METHOD_NAME, buildUrl(uri), null);
	}
    
	public String doPost(String uri, String data) {
		return sendHttpRequest(POST_METHOD_NAME, buildUrl(uri), data);
	}
    
	public String doPut(String uri, String data) {
		return sendHttpRequest(PUT_METHOD_NAME, buildUrl(uri), data);
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

	protected String getContentType(String method) {

		switch (method) {
			case GET_METHOD_NAME:
				return getRequestContentTypeForGet();
			case POST_METHOD_NAME:
				return getRequestContentTypeForPost();
			case PUT_METHOD_NAME:
				return getRequestContentTypeForPut();
			case DELETE_METHOD_NAME:
				return getRequestContentTypeForDelete();
		}

		return null;
	}

	protected Map<String, String> getHeaders(String method) {

		switch (method) {
			case GET_METHOD_NAME:
				return getRequestHeadersForGet();
			case POST_METHOD_NAME:
				return getRequestHeadersForPost();
			case PUT_METHOD_NAME:
				return getRequestHeadersForPut();
			case DELETE_METHOD_NAME:
				return getRequestHeadersForDelete();
		}

		return null;
	}

	protected String sendHttpRequest(String method, String url, String data) {
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

			boolean hasData = !Strings.isNullOrEmpty(data);
			urlConnection.setDoOutput(hasData);
			urlConnection.setDoInput(true);
			urlConnection.setRequestMethod(method);
			urlConnection.setRequestProperty("Accept-Charset", acceptCharset);
			urlConnection.setRequestProperty("Content-Type", Strings.isNullOrEmpty(contentType) ? defaultContentType : contentType);
			
			if (headers != null && !headers.isEmpty()) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					urlConnection.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}

			if (hasData) {
				try (BufferedWriter out = new BufferedWriter(
						new OutputStreamWriter(urlConnection.getOutputStream(), acceptCharset))) {
					out.write(data);
					out.flush();
					out.close();
				} finally {
					urlConnection.getOutputStream().close();
				}
			}

			urlConnection.connect();
			int responseCode = urlConnection.getResponseCode();

			log.log(Level.FINE, "Sending \"{0}\" request to URL : {1}", new Object[]{method, url});
			log.log(Level.FINE, "Response Code : {0}", responseCode);
			
			StringBuilder response;
			
			try (BufferedReader in = new BufferedReader(
					new InputStreamReader(urlConnection.getInputStream(), acceptCharset))) {
				String inputLine;
				response = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
                }
            } finally {
				urlConnection.getInputStream().close();
				urlConnection.disconnect();
			}
			
			return response.toString();
		} catch (Exception ex) {
			log.log(Level.FINE, null, ex);
		}
		return "";
	}
}