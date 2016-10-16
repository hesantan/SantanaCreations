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
package propertymanagement.proxies.zillow;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import realestate.proxies.zillow.ZillowHttpProxy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Santana Creations
 */
public class ZillowHttpProxyTest {

	private final ZillowHttpProxy _zillowHttpProxy;
	private String _baseUri;
	private List<String> _zillowWebServices;
	private List<String> _testAddresses;
	
	public ZillowHttpProxyTest() {
		String _baseUrl = "http://www.zillow.com";
		String _apiKey = "X1-ZWz19rhxouu5fv_4ye7m";
		_zillowHttpProxy = new ZillowHttpProxy(_baseUrl, _apiKey);
	}
	
	private String buildUri(String serviceUri) {
		return _baseUri + serviceUri;
	}
	
	@Before
	public void setUp() {
		_baseUri = "/webservice/";
		_zillowWebServices = Collections.singletonList(
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
		_testAddresses = Arrays.asList(
			"2114+Bigelow+Ave&citystatezip=Seattle%2C+WA", 
			"1456+Test+Address&citystatezip=Minnetonka%2C+MN+55426"
		);
	}
	
	@After
	public void tearDown() {
		_zillowWebServices = null;
		_testAddresses = null;
	}
	
	@Test
	public void doGetTest() {
		for (String zillowWebService : _zillowWebServices) {
			for (String testAddress : _testAddresses) {
				String serviceUri = zillowWebService + "?" + "address=" + testAddress;
				String responseString = _zillowHttpProxy.doGet(buildUri(serviceUri));
				Assert.assertNotEquals("A response was received for " + zillowWebService + ".", "", responseString);
			}
		}
	}
	
	@Test
	public void doPostTest() {
		String responseString = _zillowHttpProxy.doPost(null, null);
		Assert.assertEquals(responseString, "");
	}
	
	@Test
	public void doPutTest() {
		String responseString = _zillowHttpProxy.doPut(null, null);
		Assert.assertEquals(responseString, "");
	}
	
	@Test
	public void doDeleteTest() {
		String responseString = _zillowHttpProxy.doDelete(null, null);
		Assert.assertEquals(responseString, "");
	}
}
