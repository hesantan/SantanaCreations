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

package realestate.datatransferobjects;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Represents a web response from the Zillow Web Service
 *
 * @author Hector
 * @since 10/18/2016
 */
public class ZillowWebServiceResponseTest {

	private ZillowWebServiceResponse zillowWebServiceResponse;

	public ZillowWebServiceResponseTest() {
		zillowWebServiceResponse = new ZillowWebServiceResponse();
	}

	@Test
	public void membersTest() {
		Assert.assertNull(zillowWebServiceResponse.getRequest());
		Assert.assertNull(zillowWebServiceResponse.getMessage());
		Assert.assertNull(zillowWebServiceResponse.getBody());
	}

	@Test
	public void responseRequestTest() {
		ZillowResponseRequest zillowResponseRequest = new ZillowResponseRequest();
		zillowResponseRequest.setAddress("2114 Bigelow Ave");
		zillowResponseRequest.setCityStateZip("Seattle, WA");

		zillowWebServiceResponse.setRequest(zillowResponseRequest);

		Assert.assertNotNull(zillowResponseRequest.getAddress());
		Assert.assertNotNull(zillowResponseRequest.getCityStateZip());
	}

	@Test
	public void responseMessageTest() {
		ZillowResponseMessage zillowResponseMessage = new ZillowResponseMessage();
		zillowResponseMessage.setCode(0);
		zillowResponseMessage.setText("Request successfully processed");

		zillowWebServiceResponse.setMessage(zillowResponseMessage);

		Assert.assertNotNull(zillowResponseMessage.getCode());
		Assert.assertNotNull(zillowResponseMessage.getText());
	}

	@Test
	public void responseBodyTest() {
		ZillowResponseBody zillowResponseBody = new ZillowResponseBody();
		zillowResponseBody.setResults(new ArrayList<>());

		zillowWebServiceResponse.setBody(zillowResponseBody);

		Assert.assertNotNull(zillowResponseBody.getResults());
	}
}