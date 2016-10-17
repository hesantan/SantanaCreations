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

package realestate.dataaccess.zillow;

import org.junit.Assert;
import org.junit.Test;
import realestate.proxies.zillow.ZillowHttpProxy;

/**
 * Test for the Zillow Data Provider
 *
 * @author Hector
 * @since 10/16/2016
 */
public class ZillowDataProviderTest {
	private final ZillowDataProvider zillowDataProvider;
	
	public ZillowDataProviderTest() {
		ZillowHttpProxy zillowHttpProxy = new ZillowHttpProxy("http://www.zillow.com", "X1-ZWz19rhxouu5fv_4ye7m");
		zillowDataProvider = new ZillowDataProvider(zillowHttpProxy);
	}
	
	@Test
	public void getDeepSearchResultsTest() {
		Assert.assertNotEquals("", zillowDataProvider.getDeepSearchResults());
	}
	
}