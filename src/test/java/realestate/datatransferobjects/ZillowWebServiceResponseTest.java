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
	public void initTest() {
		zillowWebServiceResponse.request = new ZillowResponseRequest();
		zillowWebServiceResponse.message = new ZillowResponseMessage();
		zillowWebServiceResponse.body = new ZillowResponseBody() {
			{
				results = new ArrayList<>();
			}
		};
	}
}