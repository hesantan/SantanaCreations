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

import common.enums.HttpMethod;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Santana Creations
 */
public class ZillowHttpProxyTest {
	
	private final ZillowHttpProxy zillowHttpProxy;
	private List<String> zillowWebServices;
	private List<String> testAddresses;
	
	public ZillowHttpProxyTest() {
		String baseUrl = "http://www.zillow.com";
		String apiKey = "X1-ZWz19rhxouu5fv_4ye7m";
		zillowHttpProxy = new ZillowHttpProxy(baseUrl, apiKey);
	}
	
	@Before
	public void setUp() {
		zillowWebServices = Collections.singletonList(
			"GetDeepSearchResults.htm"
				//"GetChart.htm",
			//"GetComps.htm",
			//"GetDeepComps.htm",
			//"GetDemographics.htm",
			//"GetRegionChart.htm",
			//"GetRegionChildren.htm",
			//"GetSearchResults.htm",
			//"GetZestimate.htm"
		);
		testAddresses = Arrays.asList(
			"2114+Bigelow+Ave&citystatezip=Seattle%2C+WA", 
			"1456+Test+Address&citystatezip=Minnetonka%2C+MN+55426"
		);
	}
	
	@After
	public void tearDown() {
		zillowWebServices = null;
		testAddresses = null;
	}
	
	@Test
	public void doGetTest() {
		for (String zillowWebService : zillowWebServices) {
			for (String testAddress : testAddresses) {
				String serviceUri = zillowWebService + "?" + "address=" + testAddress;
				String responseString = zillowHttpProxy.doGet(serviceUri);
				Assert.assertNotEquals("A response was received for " + zillowWebService + ".", "", responseString);
			}
		}
	}
	
	@Test
	public void doPostTest() {
		String responseString = zillowHttpProxy.doPost(null, null);
		Assert.assertEquals(responseString, "");
	}

	@Test
	public void doEmptyPostTest() {
		String responseString = zillowHttpProxy.doPost("", "");
		Assert.assertEquals(responseString, "");
	}

	@Test
	public void doNullPostTest() {
		String serviceUri = "GetDeepSearchResults.htm?address=2114+Bigelow+Ave&citystatezip=Seattle%2C+WA";
		String responseString = zillowHttpProxy.doPost(serviceUri, "Data");
		Assert.assertNotEquals(responseString, "");
	}
	
	@Test
	public void doPutTest() {
		String responseString = zillowHttpProxy.doPut(null, null);
		Assert.assertEquals(responseString, "");
	}

	@Test
	public void doEmptyPutTest() {
		String responseString = zillowHttpProxy.doPut("", "");
		Assert.assertEquals(responseString, "");
	}
	
	@Test
	public void doDeleteTest() {
		String responseString = zillowHttpProxy.doDelete(null, null);
		Assert.assertEquals(responseString, "");
	}

	@Test
	public void doEmptyDeleteTest() {
		String responseString = zillowHttpProxy.doDelete("", "");
		Assert.assertEquals(responseString, "");
	}

	@Test
	public void doRequestContentTypeTest() {
		Assert.assertEquals(zillowHttpProxy.getRequestContentTypeForGet(), "text/xml");
		Assert.assertEquals(zillowHttpProxy.getRequestContentTypeForPost(), null);
		Assert.assertEquals(zillowHttpProxy.getRequestContentTypeForPut(), null);
		Assert.assertEquals(zillowHttpProxy.getRequestContentTypeForDelete(), null);
	}

	@Test
	public void doExceptionTest() {
		String serviceUri = "\\/\\/\\/\\";
		String responseString = zillowHttpProxy.doPost(serviceUri, serviceUri);
		Assert.assertEquals(responseString, "");
	}

	@Test
	public void doHeadersTest() {
		Assert.assertEquals(zillowHttpProxy.getHeaders(HttpMethod.GET).size(), 1);
		Assert.assertEquals(zillowHttpProxy.getHeaders(HttpMethod.POST).size(), 0);
		Assert.assertEquals(zillowHttpProxy.getHeaders(HttpMethod.PUT), null);
		Assert.assertEquals(zillowHttpProxy.getHeaders(HttpMethod.DELETE), null);
		Assert.assertEquals(zillowHttpProxy.getHeaders(HttpMethod.NONE), null);
	}

	@Test
	public void doContentTypeTest() {
		Assert.assertEquals(zillowHttpProxy.getContentType(HttpMethod.GET), "text/xml");
		Assert.assertEquals(zillowHttpProxy.getContentType(HttpMethod.POST), null);
		Assert.assertEquals(zillowHttpProxy.getContentType(HttpMethod.PUT), null);
		Assert.assertEquals(zillowHttpProxy.getContentType(HttpMethod.DELETE), null);
		Assert.assertEquals(zillowHttpProxy.getContentType(HttpMethod.NONE), null);
	}
}
