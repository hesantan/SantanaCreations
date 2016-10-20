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
package common.proxies.base;

import common.enums.HttpMethod;
import common.utilities.CustomLoggerFactory;
import common.utilities.Strings;

import java.io.*;
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
	private final String acceptCharset;
	private final String defaultContentType;
	private final Logger log;
	
	protected AbstractHttpProxy(String defaultContentType, String acceptCharset) {
		this.acceptCharset = acceptCharset;
		this.defaultContentType = defaultContentType;
		log = CustomLoggerFactory.getLogger(getClass().getName());
	}
	
	public String doGet(String uri) {
		return sendHttpRequest(HttpMethod.GET, buildUrl(uri), null);
	}
    
	public String doPost(String uri, String data) {
		return sendHttpRequest(HttpMethod.POST, buildUrl(uri), data);
	}
    
	public String doPut(String uri, String data) {
		return sendHttpRequest(HttpMethod.PUT, buildUrl(uri), data);
	}
	
	public String doDelete(String uri, String data) {
		return sendHttpRequest(HttpMethod.DELETE, buildUrl(uri), data);
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

	public String getContentType(HttpMethod method) {
		switch (method) {
			case GET:
				return getRequestContentTypeForGet();
			case POST:
				return getRequestContentTypeForPost();
			case PUT:
				return getRequestContentTypeForPut();
			case DELETE:
				return getRequestContentTypeForDelete();
			default:
				return null;
		}
	}

	public Map<String, String> getHeaders(HttpMethod method) {
		switch (method) {
			case GET:
				return getRequestHeadersForGet();
			case POST:
				return getRequestHeadersForPost();
			case PUT:
				return getRequestHeadersForPut();
			case DELETE:
				return getRequestHeadersForDelete();
			default:
				return null;
		}
	}

	protected String sendHttpRequest(HttpMethod method, String url, String data) {
		String contentType = getContentType(method);
		Map<String, String> headers = getHeaders(method);
		return sendHttpRequest(method, headers, contentType, url, data);
	}

	private String sendHttpRequest(HttpMethod method, Map<String, String> headers, String contentType, String url, String data) {
		
		if (Strings.isNullOrEmpty(url)) {
			return "";
		}
		
		try {
			HttpURLConnection urlConnection = buildUrlConnection(url, data, method, contentType);
			setUrlConnectionHeaders(urlConnection, headers);
			writeContentToUrlConnection(urlConnection, data);

			urlConnection.connect();
			int responseCode = urlConnection.getResponseCode();

			log.log(Level.FINE, "Sending \"{0}\" request to URL : {1}", new Object[]{method, url});
			log.log(Level.FINE, "Response Code : {0}", responseCode);

			StringBuilder response = getResponseFromUrlConnection(urlConnection);

			return response.toString();
		} catch (Exception ex) {
			log.log(Level.FINE, null, ex);
		}
		return "";
	}

	private HttpURLConnection buildUrlConnection(String url, String data, HttpMethod method, String contentType) throws IOException {
		URL urlObject = new URL(url);
		HttpURLConnection httpURLConnection = (HttpURLConnection) urlObject.openConnection();

		httpURLConnection.setDoOutput(!Strings.isNullOrEmpty(data));
		httpURLConnection.setDoInput(true);
		httpURLConnection.setRequestMethod(method.toString());
		httpURLConnection.setRequestProperty("Accept-Charset", acceptCharset);
		httpURLConnection.setRequestProperty("Content-Type", Strings.isNullOrEmpty(contentType) ? defaultContentType : contentType);

		return httpURLConnection;
	}

	private void writeContentToUrlConnection(HttpURLConnection httpURLConnection, String data) throws IOException {
		if (Strings.isNullOrEmpty(data)) {
			return;
		}

		try (BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(httpURLConnection.getOutputStream(), acceptCharset))) {
			out.write(data);
			out.flush();
			out.close();
		} finally {
			httpURLConnection.getOutputStream().close();
		}
	}

	private void setUrlConnectionHeaders(HttpURLConnection httpURLConnection, Map<String, String> headers) {
		if (headers != null && !headers.isEmpty()) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}
	}

	private StringBuilder getResponseFromUrlConnection(HttpURLConnection httpURLConnection) throws IOException {
		StringBuilder response;

		try (BufferedReader in = new BufferedReader(
				new InputStreamReader(httpURLConnection.getInputStream(), acceptCharset))) {
			String inputLine;
			response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		} finally {
			httpURLConnection.getInputStream().close();
			httpURLConnection.disconnect();
		}

		return response;
	}
}