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
package realestate.datatransferobjects;

/**
 *
 * @author Santana Creations
 */
public class ZillowWebServiceResponse {

	private ZillowResponseRequest request;
	private ZillowResponseMessage message;
	private ZillowResponseBody body;

	public ZillowResponseBody getBody() {
		return body;
	}

	public void setBody(ZillowResponseBody body) {
		this.body = body;
	}

	public ZillowResponseRequest getRequest() {
		return request;
	}

	public void setRequest(ZillowResponseRequest request) {
		this.request = request;
	}

	public ZillowResponseMessage getMessage() {
		return message;
	}

	public void setMessage(ZillowResponseMessage message) {
		this.message = message;
	}
}
